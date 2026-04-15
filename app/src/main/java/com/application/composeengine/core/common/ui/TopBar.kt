package com.application.composeengine.core.common.ui

import androidx.compose.foundation.layout.RowScope

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun TopBar(

    title: String,

    onBackClick: () -> Unit,

    modifier: Modifier = Modifier,

    actions: @Composable RowScope.() -> Unit = {},

    showBackButton: Boolean = true

) {

    val colorScheme = MaterialTheme.colorScheme



    TopAppBar(

        modifier = modifier,

        title = {

            Text(

                text = title,

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold,

                color = colorScheme.onBackground

            )

        },

        navigationIcon = {

            if (showBackButton) {

                IconButton(onClick = onBackClick) {

                    Icon(

                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription = "Back",

                        tint = colorScheme.onBackground

                    )

                }

            }

        },

        actions = actions,

        colors = TopAppBarDefaults.topAppBarColors(

            containerColor = colorScheme.background,

            scrolledContainerColor = colorScheme.background,

            navigationIconContentColor = colorScheme.onBackground,

            titleContentColor = colorScheme.onBackground,

            actionIconContentColor = colorScheme.onBackground

        )

    )

}

