package com.example.route_android_task.data.repository.remote

import com.example.route_android_task.data.models.ProductsResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductsResponse
}