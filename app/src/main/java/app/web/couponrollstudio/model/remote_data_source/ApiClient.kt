package app.web.couponrollstudio.model.remote_data_source

import app.web.couponrollstudio.model.Store
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {
    @GET("stores")
    suspend fun getStores(): Response<List<Store>>

    @POST("stores")
    suspend fun postStore(@Body store: Store): Response<Store>

    @GET("stores/{id}")
    suspend fun getStore(@Path("id") id: String): Response<Store>
}