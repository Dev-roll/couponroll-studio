package app.web.couponrollstudio.ui.edit_coupons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.couponrollstudio.model.Task
import app.web.couponrollstudio.model.TasksRepository
import kotlinx.coroutines.launch

class EditCouponsViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    fun completeTask(task: Task, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            tasksRepository.completeTask(task)
        } else {
            tasksRepository.activateTask(task)
        }
    }

    fun starTask(task: Task, starred: Boolean) = viewModelScope.launch {
        if (starred) {
            tasksRepository.starTask(task)
        } else {
            tasksRepository.unStarTask(task)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MyCouponsUiState(val itemList: List<Task> = listOf())
