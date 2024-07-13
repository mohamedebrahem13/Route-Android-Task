package com.example.route_android_task.domain.repository.remote

import com.example.route_android_task.data.models.ProductDTO

interface IProductRemoteDS {
    suspend fun getProducts(): List<ProductDTO>
}