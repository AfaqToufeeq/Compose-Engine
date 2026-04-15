package com.application.composeengine.domain.settings.repository

import com.application.composeengine.domain.settings.model.SettingsSection
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettingsSections(): Flow<List<SettingsSection>>
}