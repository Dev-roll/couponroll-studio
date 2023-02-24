package app.web.couponrollstudio.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.components.customTabIndicatorOffset
import app.web.couponrollstudio.ui.navigation.NavigationDestination
import app.web.couponrollstudio.ui.store_analitics.StoreAnaliticsScreen
import app.web.couponrollstudio.ui.store_coupons.StoreCouponsScreen
import app.web.couponrollstudio.ui.store_details.StoreDetailsScreen
import app.web.couponrollstudio.ui.store_settings.StoreSettingsScreen
import coil.compose.rememberAsyncImagePainter

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
) {
    val uiState: HomeViewModel.UiState by homeViewModel.uiState

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("edit_coupons") },
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
            navController = navController,
            onClick = homeViewModel::onClick,
            uiState = uiState,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    navController: NavController,
    onClick: () -> Unit,
    uiState: HomeViewModel.UiState,
    modifier: Modifier = Modifier
) {
    TabLayout(onClick = onClick, uiState = uiState, navController = navController)
}

@Composable
fun TabLayout(
    navController: NavController,

    uiState: HomeViewModel.UiState
) {
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
        val imageUrls = listOf(
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4fb77526-ffa8-4d17-92e2-6106433b82d3/Group_2608815.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150812Z&X-Amz-Expires=86400&X-Amz-Signature=1d883c9ce55397e6eb0d05908073f61b70839a7bca66df930347aebbfb5a3f87&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608815.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8c1bb77-3e47-4263-b040-8adde060e752/Group_2608810.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150816Z&X-Amz-Expires=86400&X-Amz-Signature=8230d239f6d13b47dbe0019a4f9c4d8d44612b1f8056ceaa28e40a1a87da2239&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608810.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/03e64c3a-4ef5-493e-9a41-5d0c2afcf110/Group_2608816.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150823Z&X-Amz-Expires=86400&X-Amz-Signature=68b5056a63ba78ad249de63d0d0c58d70c27fe38e61384357a10646f72da349d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608816.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3f2266ed-c1b2-4677-8e39-29b4a0a598d4/Group_2608812.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150828Z&X-Amz-Expires=86400&X-Amz-Signature=2256a01696b3a7dde8e27ef979e77b9ffd05422a433ae33885df59105f32e128&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608812.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f8a76a21-9e0e-408c-99d0-dcaa216afead/Group_2608809.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150834Z&X-Amz-Expires=86400&X-Amz-Signature=49810e8f969b04c37f06010d66089ce2989a20b7c1d5c5855f81a492f83ed0de&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608809.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b945a0f3-2a94-4447-9a37-b1dfc73706c9/Group_2608817.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150839Z&X-Amz-Expires=86400&X-Amz-Signature=341659970e28233e02d0e09eeb9fa65af9f02f38325537e5bc9ee2d54ba0ceb8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608817.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/c96cedbb-8a91-41c1-b705-9a107af390d8/Group_2608811.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150845Z&X-Amz-Expires=86400&X-Amz-Signature=def13f5f9dfec47f9a7ff80d5fcaa73764c272882267e82b5dcb851882e58ae5&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608811.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/acdee1a6-ab6b-43a8-8d31-4f5c5b3ea2f5/Group_2608818.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150851Z&X-Amz-Expires=86400&X-Amz-Signature=d0f161c56f8da0c48b1005f07bfc8438f9bdd0f34f27c6462a0da216b41fc342&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608818.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject"
        )
        Box(modifier = Modifier.height(150.dp)) {
            val painter8: Painter =
                rememberAsyncImagePainter(imageUrls[8])
            val painter9: Painter =
                rememberAsyncImagePainter(imageUrls[9])
            Image(
                painter = painter8,
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
            )
            Column(
                modifier = Modifier
                    .offset(x = 12.dp, y = 100.dp)
            ) {
                Image(
                    painter = painter9,
                    contentDescription = null,
                    modifier = Modifier
                        .height(80.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.surface, CircleShape)
                )
            }
        }
        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { navController.navigate("edit_profiles") },
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
        }
        Row(
            Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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