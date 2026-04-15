package com.application.composeengine.ui.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.composeengine.domain.settings.usecase.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSettingsUseCase()
                .collect { domainSections ->
                    val uiModels = domainSections.map { it.toUiModel() }
                    _uiState.update { it.copy(isLoading = false, sections = uiModels) }
                }
        }
    }
}