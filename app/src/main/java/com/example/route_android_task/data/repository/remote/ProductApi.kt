package com.example.route_android_task.data.repository.remote

import com.example.route_android_task.data.models.ProductDTO
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDTO>
}