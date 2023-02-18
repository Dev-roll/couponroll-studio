package app.web.couponrollstudio.ui.capture

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.AppViewModelProvider
import app.web.couponrollstudio.ui.components.CouponRollStudioTopAppBar
import app.web.couponrollstudio.ui.home.HomeViewModel
import app.web.couponrollstudio.ui.navigation.NavigationDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CaptureDestination : NavigationDestination {
    override val route = "capture"
    override val titleRes = R.string.capture_title
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CaptureScreen(
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
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val lifecycleOwner = LocalLifecycleOwner.current

    var previewUseCase by remember {
        mutableStateOf<UseCase>(Preview.Builder().build())
    }
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder().setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY).build()
        )
    }

    Scaffold(
        topBar = {
            CouponRollStudioTopAppBar(
                title = stringResource(CaptureDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        val savedFile = imageCaptureUseCase.takePicture(context.executor)
                        onImageFile(savedFile)
                        val uri = savedFile.toUri()
                        if (uri != Uri.EMPTY) {
                            val uriEncoded =
                                withContext(Dispatchers.IO) {
                                    URLEncoder.encode(
                                        uri.toString(),
                                        StandardCharsets.UTF_8.toString()
                                    )
                                }
                            navController.navigate("task_entry/${uriEncoded}")
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = stringResource(R.string.task_entry_title),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        if (cameraPermissionState.status.isGranted) {
            Text(text = "", modifier = modifier.padding(innerPadding))

            Box {
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onUseCase = { previewUseCase = it }
                )
            }
            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
                }
            }
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)


@Suppress("BlockingMethodInNonBlockingContext")
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener(
            {
                continuation.resume(future.get())
            },
            executor
        )
    }
}

suspend fun ImageCapture.takePicture(executor: Executor): File {
    val photoFile = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            File.createTempFile("image", "jpg")
        }.getOrElse { exception ->
            Log.e(
                "TakePicture",
                "Failed to create temporary file",
                exception
            )
            File("/dev/null")
        }
    }

    return suspendCoroutine { continuation ->
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        takePicture(
            outputOptions, executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    continuation.resume(photoFile)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("TakePicture", "Image capture failed", exception)
                    continuation.resumeWithException(exception)
                }
            }
        )
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    onUseCase: (UseCase) -> Unit = { }
) {
    AndroidView(factory = { context ->
        val previewView = PreviewView(context).apply {
            this.scaleType = scaleType
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        onUseCase(
            Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
        )
        previewView
    })
}