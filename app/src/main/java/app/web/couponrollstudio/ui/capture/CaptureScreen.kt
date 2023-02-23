package app.web.couponrollstudio.ui.capture

import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.AppViewModelProvider
import app.web.couponrollstudio.ui.home.HomeViewModel
import app.web.couponrollstudio.ui.navigation.NavigationDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.io.File
import java.util.concurrent.Executors

object CaptureDestination : NavigationDestination {
    override val route = "qr_code_scan"
    override val titleRes = R.string.capture_title
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanQrcodeScreen(
    navController: NavController,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToTaskEntry: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onImageFile: (File) -> Unit = { },
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {
    val permissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            permissionState.launchPermissionRequest()
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            val context = LocalContext.current
            val cameraProviderFuture = remember {
                ProcessCameraProvider.getInstance(context)
            }

            AndroidView(
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        setupCamera(
                            previewView = this,
                            lifecycleOwner = lifecycleOwner,
                            cameraProviderFuture = cameraProviderFuture,
                            onQrCodeScanned = { }
                        )
                    }
                },
            )
        }
        is PermissionStatus.Denied -> {
            Column {
                val textToShow =
                    if ((permissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                        "The camera is important for this app. Please grant the permission."
                    } else {

                        "Camera permission required for this feature to be available. " +
                                "Please grant the permission"
                    }
                Text(textToShow)
                Button(onClick = { permissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}

private fun setupCamera(
    previewView: PreviewView,
    lifecycleOwner: LifecycleOwner,
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    onQrCodeScanned: (value: String) -> Unit,
) {
    cameraProviderFuture.addListener({
        try {
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            val qrCodeAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build().apply {
                    setAnalyzer(
                        Executors.newSingleThreadExecutor(),
                        QrCordAnalyzer(onQrCodeScanned)
                    )
                }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, preview, qrCodeAnalysis
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, ContextCompat.getMainExecutor(previewView.context))
}

class QrCordAnalyzer(private val onQrDetected: (code: String) -> Unit) : ImageAnalysis.Analyzer {
    private val qrScannerOptions: BarcodeScannerOptions = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()
    private val qrScanner = BarcodeScanning.getClient(qrScannerOptions)

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null) {
            val adjustedImage =
                InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

            qrScanner.process(adjustedImage)
                .addOnSuccessListener {
                    if (it.isNotEmpty()) {
                        Log.d("Success", "Detected code is ${it[0].rawValue}")
                        onQrDetected(it[0].rawValue.toString())
                    }
                }
                .addOnCompleteListener { image.close() }
        }
    }
}
