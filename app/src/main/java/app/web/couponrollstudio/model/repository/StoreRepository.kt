package app.web.couponrollstudio.model.repository

import app.web.couponrollstudio.model.Store

interface StoreRepository {
    suspend fun getStores(): List<Store>

    suspend fun postStore(store: Store): Store

    suspend fun getStore(id: String): Store
}