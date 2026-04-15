package com.application.composeengine.data.local.datasource

import com.application.composeengine.R
import com.application.composeengine.domain.settings.model.SettingsItem
import com.application.composeengine.domain.settings.model.SettingsItemType
import com.application.composeengine.domain.settings.model.SettingsSection

class SettingsLocalDataSource {
    
    // In a app, 'currentLanguage' would be injected from DataStore/SharedPreferences
    fun fetchSettings(currentLanguage: String = "English (US)"): List<SettingsSection> {
        return listOf(
            SettingsSection(
                title = "General",
                items = listOf(
                    SettingsItem(
                        id = "language",
                        title = "Language",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.VALUE,
                        value = currentLanguage
                    ),
                    SettingsItem(
                        id = "rate_us",
                        title = "Rate Us",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.ACTION
                    ),
                    SettingsItem(
                        id = "share_app",
                        title = "Share App",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.ACTION
                    )
                )
            ),
            SettingsSection(
                title = "About",
                items = listOf(
                    SettingsItem(
                        id = "report_ai",
                        title = "Report AI Content",
                        iconRes = R.drawable,
                        type = SettingsItemType.NAVIGATION
                    ),
                    SettingsItem(
                        id = "privacy_policy",
                        title = "Privacy Policy",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.NAVIGATION
                    ),
                    SettingsItem(
                        id = "manage_sub",
                        title = "Manage Subscription",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.NAVIGATION
                    ),
                    SettingsItem(
                        id = "about_app",
                        title = "About App",
                        iconRes = R.drawable.ic_launcher_foreground,
                        type = SettingsItemType.NAVIGATION
                    )
                )
            )
        )
    }
}