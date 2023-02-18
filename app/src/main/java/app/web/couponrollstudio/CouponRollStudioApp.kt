package app.web.couponrollstudio

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.web.couponrollstudio.ui.navigation.CouponRollStudioNavHost

@Composable
fun CouponRollStudioApp(navController: NavHostController = rememberNavController()) {
    CouponRollStudioNavHost(navController = navController)
}
