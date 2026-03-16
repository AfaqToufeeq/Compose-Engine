package com.application.composeengine.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.application.composeengine.ui.features.assistant.AssistantRoute
import com.application.composeengine.ui.features.chat.AiChatRoute
import com.application.composeengine.ui.features.explore.ExploreRoute
import com.application.composeengine.ui.features.history.HistoryRoute
import com.application.composeengine.ui.features.assistants.AssistantsRoute
import com.application.composeengine.ui.navigation.Route

/**
 * MainContainerScreen
 *
 * This is the **shell of the main app**.  
 * Contains Bottom Navigation and nested navigation per tab.
 *
 * @param onNavigateToChat Navigate to a ChatSession with optional query
 * @param onNavigateToAssistant Navigate to an AssistantTool screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerScreen(
    navController: NavHostController,
    onNavigateToChat: (initialQuery: String?) -> Unit,
    onNavigateToAssistant: (toolId: String, category: String, quickPrompt: String?) -> Unit
) {
    val bottomTabs = listOf(
        Route.Explore,
        Route.AiChat,
        Route.Assistants,
        Route.History
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, tabs = bottomTabs)
        }
    ) { paddingValues ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            onNavigateToChat = onNavigateToChat,
            onNavigateToAssistant = onNavigateToAssistant
        )
    }
}

/**
 * BottomNavBar
 *
 * Professional bottom navigation that handles:
 * - Selected state
 * - SingleTop navigation
 * - State restoration
 */
@Composable
fun BottomNavBar(
    navController: NavHostController,
    tabs: List<Route>
) {
    NavigationBar {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStack?.destination?.route

        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab::class.simpleName,
                onClick = {
                    navController.navigate(tab::class.simpleName!!) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(Route.MainContainer::class.simpleName!!) {
                            saveState = true
                        }
                    }
                },
                icon = { /* TODO: Provide Icon for each tab */ },
                label = { Text(tab::class.simpleName!!) }
            )
        }
    }
}

/**
 * MainNavHost
 *
 * Nested NavHost inside MainContainer.
 * Handles all **tab-specific screens** and **dynamic feature navigation**.
 */
@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigateToChat: (initialQuery: String?) -> Unit,
    onNavigateToAssistant: (toolId: String, category: String, quickPrompt: String?) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Route.Explore::class.simpleName!!,
        modifier = modifier
    ) {

        // -------------------
        // Explore Tab
        // -------------------
        composable(Route.Explore::class.simpleName!!) {
            ExploreRoute(
                onSendQuery = { query ->
                    onNavigateToChat(query)
                },
                onSelectAssistant = { toolId, category, quickPrompt ->
                    onNavigateToAssistant(toolId, category, quickPrompt)
                }
            )
        }

        // -------------------
        // AI Chat Tab
        // -------------------
        composable(Route.AiChat::class.simpleName!!) {
            AiChatRoute(
                sessionId = null,
                initialQuery = null,
                onBackClick = { navController.popBackStack() }
            )
        }

        // -------------------
        // Assistants Tab
        // -------------------
        composable(Route.Assistants::class.simpleName!!) {
            AssistantsRoute(
                onSelectAssistant = { toolId, category, quickPrompt ->
                    onNavigateToAssistant(toolId, category, quickPrompt)
                }
            )
        }

        // -------------------
        // History Tab
        // -------------------
        composable(Route.History::class.simpleName!!) {
            HistoryRoute(
                onSelectChat = { sessionId ->
                    onNavigateToChat(sessionId)
                }
            )
        }

        // -------------------
        // Dynamic AssistantTool Route
        // -------------------
        composable<Route.AssistantTool> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.AssistantTool>()
            AssistantRoute(
                toolId = args.toolId,
                category = args.category,
                quickPrompt = args.quickPrompt,
                onBackClick = { navController.popBackStack() }
            )
        }

        // -------------------
        // Dynamic ChatSession Route
        // -------------------
        composable<Route.ChatSession> { backStackEntry ->
            val args = backStackEntry.toRoute<Route.ChatSession>()
            AiChatRoute(
                sessionId = args.sessionId,
                initialQuery = args.initialQuery,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}