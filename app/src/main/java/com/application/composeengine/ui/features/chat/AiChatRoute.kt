package com.application.composeengine.ui.features.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun AiChatRoute(
    sessionId: String?,
    initialQuery: String?, // This captures the text from Explore
    onBackClick: () -> Unit,
//    viewModel: AiChatViewModel = hiltViewModel()
) {
   /* val uiState by viewModel.uiState.collectAsState()

    // Professional lifecycle: Trigger initial query only once when entering
    LaunchedEffect(initialQuery) {
        if (!initialQuery.isNullOrBlank()) {
            viewModel.sendInitialPrompt(initialQuery)
        }
    }

    AiChatScreen(
        state = uiState,
        onSendMessage = viewModel::sendMessage,
        onBackClick = onBackClick
    )*/
}