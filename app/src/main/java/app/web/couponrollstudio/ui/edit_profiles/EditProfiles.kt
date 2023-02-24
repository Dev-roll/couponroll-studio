package app.web.couponrollstudio.ui.edit_profiles

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.components.CouponRollStudioTopAppBar
import app.web.couponrollstudio.ui.navigation.NavigationDestination

object EditProfilesDestination : NavigationDestination {
    override val route = "edit_profiles"
    override val titleRes = R.string.edit_profiles_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfilesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CouponRollStudioTopAppBar(
                title = stringResource(EditProfilesDestination.titleRes),
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primary,
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
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = stringResource(R.string.select_image),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = stringResource(R.string.select_image),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("店舗紹介を入力") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
