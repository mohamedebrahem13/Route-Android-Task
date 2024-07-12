package com.example.route_android_task.domain.repository

import com.example.route_android_task.domain.models.Product

interface IProductRepository {
    suspend fun getProducts(): List<Product>
}