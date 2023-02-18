package app.web.couponrollstudio

import android.app.Application
import app.web.couponrollstudio.model.AppContainer
import app.web.couponrollstudio.model.AppDataContainer

class CouponRollStudioApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}