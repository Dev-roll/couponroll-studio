package app.web.couponrollstudio.ui.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.web.couponrollstudio.model.Task
import app.web.couponrollstudio.model.TasksRepository
import java.util.*

class TaskEntryViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }

    suspend fun saveTask() {
        if (validateInput()) {
            tasksRepository.insertTask(taskUiState.taskDetails.toTask())
        }
    }

    private fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState) {
            title.isNotBlank()
        }
    }
}

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails(), val isEntryValid: Boolean = false
)

data class TaskDetails(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val isStarred: Boolean = false,
    val filepath: String = "",
)

fun TaskDetails.toTask(): Task = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    isStarred = isStarred,
    filePath = filepath
)

fun Task.toTaskUiState(isEntryValid: Boolean = false): TaskUiState = TaskUiState(
    taskDetails = this.toTaskDetails(), isEntryValid = isEntryValid
)

fun Task.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    isStarred = isStarred,
    filepath = filePath
)

fun TaskDetails.isValid(): Boolean {
    return title.isNotBlank()
}
