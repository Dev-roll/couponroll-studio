package app.web.couponrollstudio.model.remote_data_source

import app.web.couponrollstudio.model.Store
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient,
) : RemoteDataSource {
    override suspend fun getStores(): List<Store> {
        val response = apiClient.getStores()
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw HttpException()
    }

    override suspend fun postStore(store: Store): Store {
        val response = apiClient.postStore(store)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw HttpException()
    }

    override suspend fun getStore(id: String): Store {
        val response = apiClient.getStore(id)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw HttpException()
    }
}

class HttpException : Throwable()