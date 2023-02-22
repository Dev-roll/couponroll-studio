package app.web.couponrollstudio.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.ui.graphics.vector.ImageVector
import app.web.couponrollstudio.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Filled.ConfirmationNumber,
        unselectedIcon = Icons.Outlined.ConfirmationNumber,
        iconTextId = R.string.home_icon_text,
        titleTextId = R.string.home_title_text,
    ),
    QR_CODE_SCAN(
        selectedIcon = Icons.Filled.ConfirmationNumber,
        unselectedIcon = Icons.Outlined.ConfirmationNumber,
        iconTextId = R.string.qr_code_scan_icon_text,
        titleTextId = R.string.qr_code_scan_title_text,
    ),
}