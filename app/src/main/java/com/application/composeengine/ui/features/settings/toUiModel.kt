package com.application.composeengine.ui.features.settings

import com.application.composeengine.domain.settings.model.SettingsItem
import com.application.composeengine.domain.settings.model.SettingsItemType
import com.application.composeengine.domain.settings.model.SettingsSection

fun SettingsSection.toUiModel(): SettingsSectionUiModel {
    return SettingsSectionUiModel(
        headerTitle = this.title,
        rows = this.items.map { it.toUiModel() }
    )
}

fun SettingsItem.toUiModel(): SettingsItemUiModel {
    return SettingsItemUiModel(
        id = this.id,
        title = this.title,
        iconRes = this.iconRes,
        trailingText = this.value,
        hasNavigation = this.type != SettingsItemType.ACTION
    )
}