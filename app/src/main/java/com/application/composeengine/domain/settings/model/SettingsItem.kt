package com.application.composeengine.domain.settings.model

data class SettingsItem(
    val id: String,
    val title: String,
    val iconRes: Int,
    val type: SettingsItemType,
    val value: String? = null
)