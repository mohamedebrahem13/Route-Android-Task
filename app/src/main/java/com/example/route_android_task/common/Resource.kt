package com.example.route_android_task.common

sealed class Resource<out T> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Resource<T>()

    companion object {
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(message: String, data: T? = null): Resource<T> = Error(message, data)
    }
}
