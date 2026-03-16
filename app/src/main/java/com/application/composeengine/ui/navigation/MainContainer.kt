package com.application.composeengine.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * MainContainer: Shell of the app
 * - Holds Bottom Navigation
 * - Switches between bottom tabs
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(navController: NavHostController) {
    val bottomItems = listOf(
        Route.Explore,
        Route.AiChat,
        Route.Assistants,
        Route.History
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen,
                        onClick = {
                            navController.navigate(screen.routeString()) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(Route.MainContainer.routeString()) { saveState = true }
                            }
                        },
                        label = { Text(screen.routeString().capitalize()) },
                        icon = {}
                    )
                }
            }
        }
    ) { padding ->
        BottomNavGraph(navController, Modifier.padding(padding))
    }
}