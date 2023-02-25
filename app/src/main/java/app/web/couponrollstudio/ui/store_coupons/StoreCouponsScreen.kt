package app.web.couponrollstudio.ui.store_coupons

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.couponrollstudio.ui.theme.OffColor
import app.web.couponrollstudio.ui.theme.StarOnColor
import coil.compose.AsyncImage
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

@Composable
fun StoreCouponsScreen() {
    MyCouponsScreen(storeId = "2")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCouponsScreen(
    storeId: String = "0",
    modifier: Modifier = Modifier,
) {
    val myCoupons = listOf(
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "5% off",
            description = "5% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 5,
            isStarred = true,
            isUsed = false,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "クーポン",
            description = "10% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 10,
            isStarred = false,
            isUsed = true,
        ),
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "8% off クーポン",
            description = "8% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 8,
            isStarred = false,
            isUsed = true,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "20% off",
            description = "20% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 20,
            isStarred = true,
            isUsed = false,
        ),
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "クーポン",
            description = "5% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 5,
            isStarred = true,
            isUsed = true,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "10% off",
            description = "10% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 10,
            isStarred = false,
            isUsed = true,
        ),
    )

    Scaffold() { innerPadding ->
        HomeBody(
            storeId = storeId,
            itemList = myCoupons,
//            onTaskClick = navigateToTaskUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

data class Coupon(
    val id: String,
    val storeId: String,
    val storeName: String,
    val name: String,
    val description: String,
    val creatorId: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val expiresAt: String,
    val iconUrl: String,
    val imgUrl: String,
    val discount: Int,
    val isUsed: Boolean = false,
    val isStarred: Boolean = false,
)

@Composable
private fun HomeBody(
    storeId: String = "1",
    itemList: List<Coupon>,
//    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (itemList.isEmpty()) {
        Text(
            text = "クーポンがありません",
            style = MaterialTheme.typography.headlineSmall
        )
    } else {
        TaskList(
            storeId = storeId,
            itemList = itemList,
//            onTaskClick = { onTaskClick(it.id) },
        )
    }
}

@Composable
private fun TaskList(
    storeId: String = "0",
    itemList: List<Coupon>,
//    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { constraints.maxWidth }
        Column(
            modifier = if (storeId == "0") {
                Modifier
                    .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp)
                    .verticalScroll(rememberScrollState())
            } else {
                Modifier
                    .padding(all = 8.dp)
                    .verticalScroll(rememberScrollState())
            },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (couponData in itemList) {
                if ((storeId != "0" && couponData.storeId == storeId) || storeId == "0") {
                    CouponItem(
                        coupon = couponData,
//                    onTaskClick = onTaskClick,
//                    onCompletedChange = { onTaskCheckedChange(couponData, it) },
//                    onStarredChange = { onTaskStarredChange(couponData, it) },
                        width = screenWidth.toDouble()
                    )
                }
            }
        }
    }

//    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        items(items = itemList, key = { it.id }) { item ->
//            TaskItem(
//                task = item,
//                onTaskClick = onTaskClick,
//                onCompletedChange = { onTaskCheckedChange(item, it) },
//                onStarredChange = { onTaskStarredChange(item, it) }
//            )
//            Divider()
//        }
//    }
}

@Composable
fun CouponItem(
    coupon: Coupon,
//    onTaskClick: (Task) -> Unit,
//    onCompletedChange: (Boolean) -> Unit,
//    onStarredChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Double
) {
    var isModalOpen by remember { mutableStateOf(false) }
    Box(modifier = modifier
        .clickable {
            isModalOpen = true
        }
        .background(
            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
            shape = RoundedCornerShape(8.dp)
        ),
        contentAlignment = Alignment.CenterStart
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    var isUsed by remember {
                        mutableStateOf(coupon.isUsed)
                    }
                    IconToggleButton(
                        checked = coupon.isUsed,
                        onCheckedChange = { isUsed = !isUsed },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(56.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "-",
                                color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Column {
                                Text(
                                    text = "2023",
                                    color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground,
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "2.28",
                                    color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(32.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                    }
                    Icon(
                        imageVector = Icons.Rounded.QrCode,
                        contentDescription = null,
                        tint = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(32.dp)
                    )
                    if (isModalOpen) {
                        AlertDialog(
                            onDismissRequest = { isModalOpen = false },
                            title = { Text(text = "クーポン名") },
                            text = {
                                Column {
                                    Text(text = coupon.storeName)
                                    QRCode(
                                        text = "https://example.com",
                                        modifier = Modifier.size(320.dp)
                                    )
                                }
                            },
                            confirmButton = {},
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        isModalOpen = false
                                    },
                                ) { Text(text = "閉じる") }
                            }
                        )
                    }
                }
            }
            Box {
                if (coupon.isUsed) {
                    Box(
                        modifier = Modifier
                            .padding(start = (0.5).dp)
                            .height(144.dp)
                            .width(3.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background
                            )
                    ) {
                    }
                } else {
                    Column {
                        for (i in 0..12) {
                            Spacer(modifier = Modifier.height(3.dp))
                            Box(
                                modifier = Modifier
                                    .height(4.dp)
                                    .width(4.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.background,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(132.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(40.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Storefront,
                            contentDescription = null,
                            tint = OffColor,
                            modifier = Modifier.size(28.dp)
                        )
                        AsyncImage(
                            model = coupon.iconUrl,
                            contentDescription = null,
                            modifier = Modifier.clip(CircleShape),
                        )
                        if (coupon.isUsed) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.secondaryContainer.copy(
                                            alpha = 0.7f
                                        ),
                                        shape = RoundedCornerShape(40.dp)
                                    )
                            ) {}
                        }
                    }
                    Column {
                        Text(
                            text = coupon.name,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = coupon.storeName,
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val baseModifier = remember {
                        Modifier.alignByBaseline()
                    }
                    Text(
                        text = coupon.discount.toString(),
                        fontWeight = if (coupon.isUsed) FontWeight.Medium else FontWeight.Bold,
                        fontSize = 50.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "%",
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "OFF",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .width(148.dp)
                        .height(148.dp),
                ) {
                    AsyncImage(
                        model = coupon.imgUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        alpha = if (coupon.isUsed) 0.5f else 1f,
                    )
                }
                var isStarred by remember {
                    mutableStateOf(coupon.isStarred)
                }
                IconToggleButton(
                    checked = coupon.isStarred,
                    onCheckedChange = { isStarred = !isStarred },
                ) {
                    Icon(
                        imageVector = if (coupon.isStarred) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = if (coupon.isStarred) "check on" else "check off",
                        tint = if (coupon.isStarred) StarOnColor else OffColor
                    )
                }
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .absoluteOffset(x = 16.dp, y = 58.dp)
                        .background(
                            color = if (coupon.isUsed)
                                alphaBlend(
                                    MaterialTheme.colorScheme.secondaryContainer,
                                    MaterialTheme.colorScheme.background,
                                    0.5f
                                ) else MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(32.dp)
                        )
                ) {
                }
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .absoluteOffset(x = 32.dp, y = 58.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                        )
                ) {
                }
            }
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(x = (-12).dp, y = 0.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(x = (screenWidth - 24).dp, y = 0.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .absoluteOffset(x = 70.dp, y = (-74).dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .absoluteOffset(x = 70.dp, y = 74.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
        }
    }
}

@Composable
fun QRCode(text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = generateQRCode(text, context)
    val painter: Painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = "QR code for $text",
        contentScale = ContentScale.Fit,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun generateQRCode(text: String, context: Context): android.graphics.Bitmap {
    val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
    hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512, hints)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bmp =
        android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bmp.setPixel(
                x,
                y,
                if (bitMatrix[x, y]) {
                    MaterialTheme.colorScheme.onSecondaryContainer.toArgb()
                } else {
                    MaterialTheme.colorScheme.onSecondary.toArgb()
                }
            )
        }
    }
    return bmp
}

fun alphaBlend(color1: Color, color2: Color, alpha: Float): Color {
    val r = color1.red * (1 - alpha) + color2.red * alpha
    val g = color1.green * (1 - alpha) + color2.green * alpha
    val b = color1.blue * (1 - alpha) + color2.blue * alpha
    val a = color1.alpha * (1 - alpha) + color2.alpha * alpha
    return Color(r, g, b, a)
}