package app.web.couponrollstudio.model.repository

import app.web.couponrollstudio.model.Store
import app.web.couponrollstudio.model.remote_data_source.RemoteDataSource
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : StoreRepository {
    override suspend fun getStores(): List<Store> {
        return remoteDataSource.getStores()
    }

    override suspend fun postStore(store: Store): Store {
        return remoteDataSource.postStore(store)
    }

    override suspend fun getStore(id: String): Store {
        return remoteDataSource.getStore(id)
    }
}