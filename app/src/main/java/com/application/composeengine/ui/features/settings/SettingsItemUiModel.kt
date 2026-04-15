package com.application.composeengine.ui.features.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsItemUiModel(
    val id: String,
    val title: String,
    val iconRes: Int,
    val trailingValue: String? = null,
    val showChevron: Boolean = true
)