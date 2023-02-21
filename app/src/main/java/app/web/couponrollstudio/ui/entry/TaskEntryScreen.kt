package app.web.couponrollstudio.ui.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.web.couponrollstudio.R
import app.web.couponrollstudio.ui.AppViewModelProvider
import app.web.couponrollstudio.ui.components.CouponRollStudioTopAppBar
import app.web.couponrollstudio.ui.navigation.NavigationDestination
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch


object TaskEntryDestination : NavigationDestination {
    override val route = "task_entry/{uri}"
    override val titleRes = R.string.task_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: TaskEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    uri: String
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CouponRollStudioTopAppBar(
                title = stringResource(TaskEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        TaskEntryBody(
            taskUiState = viewModel.taskUiState,
            onTaskValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTask()
                    navigateToHome()
                }
            },
            modifier = modifier.padding(innerPadding),
            uri = uri
        )
    }
}

@Composable
fun TaskEntryBody(
    taskUiState: TaskUiState,
    onTaskValueChange: (TaskDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    uri: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TaskInputForm(taskDetails = taskUiState.taskDetails, onValueChange = onTaskValueChange)
        Button(
            onClick = {
                onTaskValueChange(taskUiState.taskDetails.copy(filepath = uri))
                onSaveClick()
            },
            enabled = taskUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.AddTask,
                    contentDescription = stringResource(R.string.save_action)
                )
                Spacer(Modifier.size(8.dp))
                Text(stringResource(R.string.save_action))
            }
        }
        Text(text = uri)
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(uri),
            contentDescription = "captured image"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputForm(
    taskDetails: TaskDetails,
    modifier: Modifier = Modifier,
    onValueChange: (TaskDetails) -> Unit = {},
    enabled: Boolean = true
) {
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = taskDetails.title,
            onValueChange = { onValueChange(taskDetails.copy(title = it)) },
            label = { Text(stringResource(R.string.task_title_req)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Checklist, contentDescription = stringResource(
                        id = R.string.task_title_req
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(titleFocusRequester),
            enabled = enabled,
            singleLine = true,
//            keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        OutlinedTextField(
            value = taskDetails.description,
            onValueChange = { onValueChange(taskDetails.copy(description = it)) },
            label = { Text(stringResource(R.string.task_description_req)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Notes, contentDescription = stringResource(
                        id = R.string.task_description_req
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )
    }

    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
    }
}
