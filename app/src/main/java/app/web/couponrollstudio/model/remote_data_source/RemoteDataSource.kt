package app.web.couponrollstudio.model.remote_data_source

import app.web.couponrollstudio.model.Store

interface RemoteDataSource {
    suspend fun getStores(): List<Store>

    suspend fun postStore(store: Store): Store

    suspend fun getStore(id: String): Store
}