package app.web.couponrollstudio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.web.couponrollstudio.ui.capture.CaptureDestination
import app.web.couponrollstudio.ui.home.HomeDestination
import app.web.couponrollstudio.ui.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberCouponRollStudioAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): CouponRollStudioAppState {
    return remember(navController, coroutineScope) {
        CouponRollStudioAppState(navController, coroutineScope)
    }
}

@Stable
class CouponRollStudioAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HomeDestination.route -> TopLevelDestination.HOME
            CaptureDestination.route -> TopLevelDestination.QR_CODE_SCAN
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigate(HomeDestination.route)
            TopLevelDestination.QR_CODE_SCAN -> navController.navigate(CaptureDestination.route)
        }
    }
}
