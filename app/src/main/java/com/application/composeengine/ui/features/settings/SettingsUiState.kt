package com.application.composeengine.ui.features.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiState(
    val isLoading: Boolean = true,
    val sections: List<SettingsSectionUiModel> = emptyList(),
    val error: String? = null
)