package com.example.route_android_task.data.repository.remote

import com.example.route_android_task.data.models.ProductDTO
import com.example.route_android_task.domain.repository.remote.IProductRemoteDS

class ProductRemoteDS (private val productApi: ProductApi): IProductRemoteDS {

    override suspend fun getProducts(): List<ProductDTO> {
        return productApi.getProducts().products
    }
}