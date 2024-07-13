package com.example.route_android_task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route_android_task.common.Resource
import com.example.route_android_task.domain.inetractor.GetProductsUseCase
import com.example.route_android_task.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val products: Flow<Resource<List<Product>>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProductsUseCase().collect {
                _products.value = it
            }
        }
    }
}