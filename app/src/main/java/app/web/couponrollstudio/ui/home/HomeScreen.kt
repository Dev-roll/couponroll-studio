package app.web.couponrollstudio.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.couponrollstudio.R
import app.web.couponrollstudio.model.Task
import app.web.couponrollstudio.ui.AppViewModelProvider
import app.web.couponrollstudio.ui.components.customTabIndicatorOffset
import app.web.couponrollstudio.ui.navigation.NavigationDestination
import app.web.couponrollstudio.ui.store_analitics.StoreAnaliticsScreen
import app.web.couponrollstudio.ui.store_coupons.StoreCouponsScreen
import app.web.couponrollstudio.ui.store_details.StoreDetailsScreen
import app.web.couponrollstudio.ui.store_settings.StoreSettingsScreen

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToCapture: () -> Unit,
    navigateToTaskUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToCapture,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.task_entry_title),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            onTaskClick = navigateToTaskUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TabLayout()

}

@Composable
fun TabLayout() {
//    var selectedTabIndex by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        stringResource(R.string.store_coupons_title),
        stringResource(R.string.store_details_title),
        stringResource(R.string.store_analitics_title),
        stringResource(R.string.store_settings_title)
    )

    // Reuse the default offset animation modifier, but use our own indicator
    Column() {
        Row(Modifier.padding(all = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Rounded.Code, contentDescription = null)
            Text(text = "Devroll", fontSize = 20.sp)
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = "1,000",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
        }
        TextButton(
            onClick = {},
            colors =
            ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.padding()
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = stringResource(R.string.edit_profile)
                )
            }
        }
        CustomTabRow(
            tabs = titles,
            selectedTabIndex = state,
            onTabClick = { state = it }
        )
        Column {
            when (state) {
                0 -> StoreCouponsScreen()
                1 -> StoreDetailsScreen()
                2 -> StoreAnaliticsScreen()
                3 -> StoreSettingsScreen()
                else -> Text(text = "TabLayout $state")
            }
        }
    }
}

@Composable
fun CustomTabRow(
    tabs: List<String>, selectedTabIndex: Int, onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(index) },
                text = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { (textLayoutResult.size.width + 100).toDp() }
                        },
                    )
                }
            )
        }
    }
}