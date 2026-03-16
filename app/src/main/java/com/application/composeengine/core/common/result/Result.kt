package com.application.composeengine.core.common.result

import com.application.composeengine.core.common.error.AppError

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Failure(val error: AppError) : Result<Nothing>
}