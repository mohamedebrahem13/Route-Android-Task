package com.example.route_android_task.domain.inetractor

import com.example.route_android_task.common.Resource
import com.example.route_android_task.domain.models.Product
import com.example.route_android_task.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductsUseCase (private val productRepository: IProductRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val products = productRepository.getProducts()
            emit(Resource.Success(products))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}