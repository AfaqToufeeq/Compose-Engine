package com.application.composeengine.data.repository

import com.application.composeengine.data.local.datasource.SettingsLocalDataSource
import com.application.composeengine.domain.settings.model.SettingsSection
import com.application.composeengine.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource
) : SettingsRepository {
    
    override fun getSettingsSections(): Flow<List<SettingsSection>> = flow {
        emit(localDataSource.fetchSettings())
    }
}