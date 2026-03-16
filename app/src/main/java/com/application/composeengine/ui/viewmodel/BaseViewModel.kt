package com.application.composeengine.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.composeengine.core.common.error.AppError
import com.application.composeengine.core.common.uistate.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<T>>(UIState.Loading)
    
    val uiState: StateFlow<UIState<T>> = _uiState.asStateFlow()

    /**
     * Updates the current UI state. 
     * Use this in subclasses to transition between Loading, Success, and Error.
     */
    protected fun updateState(newState: UIState<T>) {
        _uiState.value = newState
    }

    /**
     * Helper to quickly set an error state using your AppError class.
     */
    protected fun setError(error: AppError) {
        updateState(UIState.Error(error))
    }

    /**
     * Helper to quickly set a success state.
     */
    protected fun setSuccess(data: T) {
        updateState(UIState.Success(data))
    }

    /**
     * Helper to show loading state.
     */
    protected fun showLoading() {
        updateState(UIState.Loading)
    }

    /**
     * Generic coroutine launcher that automatically
     * handles Loading, Success and Error states
     */
    protected fun launchSafely(
        block: suspend () -> T
    ) {

        viewModelScope.launch {

            showLoading()

            try {

                val result = block()

                setSuccess(result)

            } catch (e: Exception) {

                setError(AppError.Unknown(e))

            }
        }
    }
}