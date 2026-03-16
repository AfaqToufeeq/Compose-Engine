package com.application.composeengine.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.application.composeengine.ui.navigation.Route

@Composable
fun SplashRoute(
    onNavigate: (Route) -> Unit,
//    viewModel: SplashViewModel = hiltViewModel()
) {
    // Observe navigation events from ViewModel
    /*    LaunchedEffect(Unit) {
            viewModel.navigationEvent.collect { targetRoute ->
                onNavigate(targetRoute)
            }
        }

        // Pass only what the screen needs
        SplashScreen() */
}