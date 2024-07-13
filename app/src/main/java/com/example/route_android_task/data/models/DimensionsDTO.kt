package com.example.route_android_task.data.models

import com.google.gson.annotations.SerializedName

data class DimensionsDTO(
    @SerializedName("width") val width: Double?,
    @SerializedName("height") val height: Double?,
    @SerializedName("depth") val depth: Double?
)