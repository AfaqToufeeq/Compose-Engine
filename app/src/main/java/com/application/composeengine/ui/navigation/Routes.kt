package com.application.composeengine.ui.navigation

import kotlinx.serialization.Serializable

/**
 * RootNavHost / Route System
 *
 * This is the **centralized navigation system** for the app.
 * All routes (screens) live here to ensure type-safety, maintainability, and refactor-proof navigation.
 *
 * Advantages:
 * - Type-safe arguments (no runtime crashes for missing arguments)
 * - Centralized, easy-to-refactor
 * - Supports Bottom Navigation, nested graphs, dynamic screens
 * - Future-proof for SavedStateHandle, deep links, and Compose Multiplatform
 */
sealed interface Route {

    // ========================
    // Root-level Destinations
    // ========================
    @Serializable
    data object Splash : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Paywall : Route

    @Serializable
    data object MainContainer : Route
    // This is the "Shell" of the app
    // Holds the Bottom Navigation (Explore, AI Chat, Assistants, History)
    // All main app screens are children of this container

    // ========================
    // Bottom Navigation Tabs
    // ========================
    @Serializable
    data object Explore : Route

    @Serializable
    data object AiChat : Route

    @Serializable
    data object Assistants : Route

    @Serializable
    data object History : Route

    // ========================
    // Feature Details (Dynamic & Centralized)
    // ========================

    /**
     * Represents a chat session, can hold an optional sessionId
     * and an initial query (used when navigating from Explore with typed input)
     */
    @Serializable
    data class ChatSession(
        val sessionId: String? = null,
        val initialQuery: String? = null
    ) : Route

    /**
     * Generic Assistant Tool Screen
     * - toolId: unique ID for the assistant type ("email_writer", "web_search", etc.)
     * - category: logical category ("Writing", "Web", "Email", etc.)
     * - quickPrompt: optional pre-filled prompt selected by the user
     *
     * This is fully dynamic and reusable across assistant types.
     */
    @Serializable
    data class AssistantTool(
        val toolId: String,
        val category: String,
        val quickPrompt: String? = null
    ) : Route
}