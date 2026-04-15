package com.application.composeengine.ui.features.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsRoute(
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onItemClick = { itemId -> 
            onNavigate(itemId)
        },
        onUpgradePremiumClick = { onNavigate("premium_upgrade") }
    )
}