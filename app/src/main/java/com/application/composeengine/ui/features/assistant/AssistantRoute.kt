package com.application.composeengine.ui.features.assistant

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AssistantRoute(
    toolId: String,
    quickPrompt: String?,
    onBackClick: () -> Unit,
//    viewModel: AssistantViewModel = hiltViewModel()
) {
/*    val uiState by viewModel.uiState.collectAsState()

    // Professional logic: If a quick prompt was passed, trigger it in the VM
    LaunchedEffect(quickPrompt) {
        quickPrompt?.let { viewModel.onQuickPromptSelected(it) }
    }

        AssistantToolScreen(
            state = uiState,
            onBackClick = onBackClick,
            onAction = viewModel::processAction
        )*/

}