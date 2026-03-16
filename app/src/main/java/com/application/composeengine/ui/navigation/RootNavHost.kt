package com.application.composeengine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.application.composeengine.ui.features.assistant.AssistantRoute
import com.application.composeengine.ui.features.chat.AiChatRoute
import com.application.composeengine.ui.features.splash.SplashRoute

@Composable
fun RootNavHost(
    navController: NavHostController,
    startDestination: Route = Route.Splash
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Route.Splash> {
            SplashRoute {
                navController.navigate(Route.Onboarding) {
                    popUpTo(Route.Splash) { inclusive = true }
                }
            }
        }

        composable<Route.Onboarding> {
            OnboardingRoute {
                navController.navigate(Route.MainContainer) {
                    popUpTo(Route.Splash) { inclusive = true }
                }
            }
        }


        // --- MAIN CONTAINER (Explore, History, etc.) ---
        composable<Route.MainContainer> {
            // This screen typically contains the BottomBar/Sheet and a sub-NavHost
            MainContainerScreen(
                onNavigateToChat = { query ->
                    navController.navigate(Route.ChatSession(initialQuery = query))
                },
                onNavigateToAssistant = { toolId, category, quickPrompt ->
                    navController.navigate(Route.AssistantTool(
                        toolId = toolId,
                        category = category,
                        quickPrompt = quickPrompt
                    ))
                }
            )
        }

        // =====================
        // Dynamic AI Chat Session
        // =====================
        composable<Route.ChatSession> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.ChatSession>()
            AiChatRoute(
                sessionId = args.sessionId,
                initialQuery = args.initialQuery,
                onBackClick = { navController.popBackStack() }
            )
        }

        // =====================
        // Dynamic Assistant Tool
        // =====================
        composable<Route.AssistantTool> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.AssistantTool>()
            AssistantRoute(
                toolId = args.toolId,
                quickPrompt = args.quickPrompt,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun OnboardingRoute(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}