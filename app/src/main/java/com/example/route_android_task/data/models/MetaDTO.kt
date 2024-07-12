package com.example.route_android_task.data.models

import com.google.gson.annotations.SerializedName

data class MetaDTO(
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("updatedAt") val updatedAt: String?,
    @SerializedName("barcode") val barcode: String?,
    @SerializedName("qrCode") val qrCode: String?
)