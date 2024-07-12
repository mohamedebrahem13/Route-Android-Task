package com.example.route_android_task.data.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products") val products: List<ProductDTO>
)