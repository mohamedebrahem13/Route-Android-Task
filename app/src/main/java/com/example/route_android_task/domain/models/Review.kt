package com.example.route_android_task.domain.models

data class Review(
    val rating: Int,
    val comment: String,
    val date: String, // Assuming you handle nullable dates appropriately
    val reviewerName: String,
    val reviewerEmail: String
)