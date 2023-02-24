package app.web.couponrollstudio.ui.edit_coupons

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.components.CouponRollStudioTopAppBar
import app.web.couponrollstudio.ui.navigation.NavigationDestination

object EditCouponsDestination : NavigationDestination {
    override val route = "edit_coupons"
    override val titleRes = R.string.edit_coupons_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCouponsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CouponRollStudioTopAppBar(
                title = stringResource(EditCouponsDestination.titleRes),
                canNavigateBack = false,
                actions = {
                    TextButton(
                        onClick = { navController.navigate("home") }
                    ) {
                        Text(text = "保存")
                    }
                }
            )
        },
    ) { innerPadding ->
        HomeBody(
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier
) {
    TaskList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskList(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { constraints.maxWidth }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 80.dp, end = 8.dp, bottom = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var discountPercent by remember { mutableStateOf(TextFieldValue("")) }
            var maxDiscount by remember { mutableStateOf(TextFieldValue("")) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.ConfirmationNumber,
                    contentDescription = null,
                )
                Text(text = "割引")
                TextField(
                    value = discountPercent,
                    onValueChange = { discountPercent = it },
                    label = { Text("割引率") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.width(100.dp)
                )
                Text(text = "%引")
                TextField(
                    value = maxDiscount,
                    onValueChange = { maxDiscount = it },
                    label = { Text("上限額") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.width(160.dp)
                )
                Text(text = "円まで")
            }
            var discountYen by remember { mutableStateOf(TextFieldValue("")) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = discountYen,
                    onValueChange = { discountYen = it },
                    label = { Text("割引額") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.width(160.dp)
                )
                Text(text = "円引")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Schedule,
                    contentDescription = null,
                )
                Text(text = "有効期限")
                val deadlineChecked = remember { mutableStateOf(false) }

                Column(modifier = Modifier.padding(16.dp)) {
                    Switch(
                        checked = deadlineChecked.value,
                        onCheckedChange = { deadlineChecked.value = it },
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(text = "まで")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.ContentCopy,
                    contentDescription = null,
                )
                Text(text = "併用")
                val concomitantUseChecked = remember { mutableStateOf(false) }

                Column(modifier = Modifier.padding(16.dp)) {
                    Switch(
                        checked = concomitantUseChecked.value,
                        onCheckedChange = { concomitantUseChecked.value = it },
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(text = "不可")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Image,
                    contentDescription = null,
                )
                Text(text = "画像")
                val imageChecked = remember { mutableStateOf(true) }

                Column(modifier = Modifier.padding(16.dp)) {
                    Switch(
                        checked = imageChecked.value,
                        onCheckedChange = { imageChecked.value = it },
                        modifier = Modifier.padding(8.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.PhotoCamera,
                        contentDescription = stringResource(R.string.select_image),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
