package com.application.composeengine.domain.settings.model

data class SettingsSection(
    val title: String,
    val items: List<SettingsItem>
)