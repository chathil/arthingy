package com.example.wikipix.data

sealed class Resource<out T>() {
    data class Success<T>(val data: T) : Resource<T>()
    data class Loading<T>(val data: T?) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}
