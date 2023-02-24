package app.web.couponrollstudio.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import app.web.couponrollstudio.CouponRollStudioApplication

object AppViewModelProvider {
    val Factory = viewModelFactory { }
}

fun CreationExtras.couponRollStudioApplication(): CouponRollStudioApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CouponRollStudioApplication)