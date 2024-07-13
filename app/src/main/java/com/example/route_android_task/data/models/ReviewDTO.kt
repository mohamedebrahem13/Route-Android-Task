package com.example.route_android_task.data.models

import com.google.gson.annotations.SerializedName

data class ReviewDTO(
    @SerializedName("rating") val rating: Int?,
    @SerializedName("comment") val comment: String?,
    @SerializedName("date") val date: String?, // Assuming you handle nullable dates appropriately
    @SerializedName("reviewerName") val reviewerName: String?,
    @SerializedName("reviewerEmail") val reviewerEmail: String?
)