package com.application.composeengine.core.common.uistate

import com.application.composeengine.core.common.error.AppError

sealed interface UIState<out T> {
    data object Loading : UIState<Nothing>
    data class Success<T>(val data: T) : UIState<T>
    data class Error(val error: AppError) : UIState<Nothing>
}