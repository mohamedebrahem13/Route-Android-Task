package com.example.route_android_task.di

import com.example.route_android_task.data.repository.ProductRepository
import com.example.route_android_task.data.repository.remote.ProductApi
import com.example.route_android_task.data.repository.remote.ProductRemoteDS
import com.example.route_android_task.domain.inetractor.GetProductsUseCase
import com.example.route_android_task.domain.repository.IProductRepository
import com.example.route_android_task.domain.repository.remote.IProductRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {


    @Provides
    fun provideProductRemoteDS(
        productApi: ProductApi
    ): IProductRemoteDS {
        return ProductRemoteDS(productApi)
    }

    @Provides
    fun provideProductRepository(
        productRemoteDS: IProductRemoteDS
    ): IProductRepository {
        return ProductRepository(productRemoteDS)
    }
    @Provides
    fun provideGetProductsUseCase(productRepository: IProductRepository): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }
}