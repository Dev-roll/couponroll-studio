package app.web.couponrollstudio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.web.couponrollstudio.ui.capture.CaptureDestination
import app.web.couponrollstudio.ui.capture.ScanQrcodeScreen
import app.web.couponrollstudio.ui.edit_coupons.EditCouponsDestination
import app.web.couponrollstudio.ui.edit_coupons.EditCouponsScreen
import app.web.couponrollstudio.ui.edit_profiles.EditProfilesDestination
import app.web.couponrollstudio.ui.edit_profiles.EditProfilesScreen
import app.web.couponrollstudio.ui.home.HomeDestination
import app.web.couponrollstudio.ui.home.HomeScreen

@Composable
fun CouponRollStudioNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = EditCouponsDestination.route,
//        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCapture = { navController.navigate(CaptureDestination.route) },
                navigateToTaskUpdate = { navController.navigateUp() },
            )
        }
        composable(route = CaptureDestination.route) {
            ScanQrcodeScreen(
                navController = navController,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navigateToTaskEntry = { },
            )
        }
        composable(route = EditProfilesDestination.route) {
            EditProfilesScreen()
        }
        composable(route = EditCouponsDestination.route) {
            EditCouponsScreen()
        }
    }
}