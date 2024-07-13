package com.example.route_android_task.data.repository

import com.example.route_android_task.data.mapper.ProductMapper
import com.example.route_android_task.domain.models.Product
import com.example.route_android_task.domain.repository.IProductRepository
import com.example.route_android_task.domain.repository.remote.IProductRemoteDS

class ProductRepository (private val productRemoteDS: IProductRemoteDS):IProductRepository {

   override suspend fun getProducts(): List<Product> {
        val result =  productRemoteDS.getProducts()
        return ProductMapper.dtoListToDomainList(result)
    }
}