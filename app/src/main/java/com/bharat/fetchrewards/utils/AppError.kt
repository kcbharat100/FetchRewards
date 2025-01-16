package com.bharat.fetchrewards.utils

sealed class AppError {
    data object NetworkError : AppError()
    data class HttpError(val code: Int, val message: String?) : AppError()
    data class UnexpectedError(val message: String?) : AppError()
}