package app.web.couponrollstudio.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.web.couponrollstudio.CouponRollStudioApplication
import app.web.couponrollstudio.ui.entry.TaskEntryViewModel
import app.web.couponrollstudio.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { TaskEntryViewModel(couponRollStudioApplication().container.tasksRepository) }

        initializer { HomeViewModel(couponRollStudioApplication().container.tasksRepository) }
    }

}

fun CreationExtras.couponRollStudioApplication(): CouponRollStudioApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CouponRollStudioApplication)