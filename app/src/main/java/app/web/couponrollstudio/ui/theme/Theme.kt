package app.web.couponrollstudio.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun CouponRollStudioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isEnableDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isUseDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && isEnableDynamicColor
    val colorScheme = when {
        isUseDynamicColor && darkTheme -> dynamicDarkColorScheme(context)
        isUseDynamicColor && !darkTheme -> dynamicLightColorScheme(context)
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}