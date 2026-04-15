package com.application.composeengine.domain.settings.usecase

import com.application.composeengine.domain.settings.model.SettingsSection
import com.application.composeengine.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<List<SettingsSection>> {
        return repository.getSettingsSections()
    }
}