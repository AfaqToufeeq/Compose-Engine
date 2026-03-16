package com.application.composeengine.core.common.error

sealed class AppError {
    data class Network(val message: String, val code: Int? = null) : AppError()
    data class Database(val message: String) : AppError()
    data class Server(val message: String, val statusCode: Int) : AppError()
    data class Unknown(val throwable: Throwable? = null) : AppError()

    fun getErrorMessage(): String = when (this) {
        is Network -> message
        is Database -> message
        is Server -> message
        is Unknown -> throwable?.message ?: "An unexpected error occurred"
    }
}