package com.bharat.fetchrewards.utils


sealed class Resource< out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: AppError) : Resource<T>()
    object Loading : Resource<Nothing>()
}