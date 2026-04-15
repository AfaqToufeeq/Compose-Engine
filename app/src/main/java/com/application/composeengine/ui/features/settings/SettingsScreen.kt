package com.application.composeengine.ui.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.composeengine.R
import com.application.composeengine.core.common.ui.TopBar
import com.application.composeengine.ui.theme.ComposeEngineTheme

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    onUpgradePremiumClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Setting",
                onBackClick = onBackClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item(key = "premium_banner", contentType = "banner") {
                PremiumBanner(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                    onClick = onUpgradePremiumClick
                )
            }

            uiState.sections.forEach { section ->
                item(key = "header_${section.title}", contentType = "header") {
                    SectionHeader(title = section.title)
                }

                items(
                    items = section.items,
                    key = { it.id },
                    contentType = { "settings_row" }
                ) { itemUiModel ->
                    SettingsRow(
                        uiModel = itemUiModel,
                        onClick = { onItemClick(itemUiModel.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsRow(
    uiModel: SettingsItemUiModel,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(uiModel.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = uiModel.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )

            if (uiModel.trailingValue != null) {
                Text(
                    text = uiModel.trailingValue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            if (uiModel.showChevron) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PremiumBanner(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val gradientBrush = remember {
        Brush.linearGradient(colors = listOf(Color(0xFF6B42D1), Color(0xFFB06AB3)))
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .background(gradientBrush)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "PREMIUM PLAN!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Unlock all premium features",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF6B42D1)),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "UPGRADE NOW", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                }
            }
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Premium Bot",
                modifier = Modifier.size(80.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 8.dp, end = 24.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    ComposeEngineTheme {
        SettingsScreen(
            uiState = SettingsUiState(
                isLoading = false,
                sections = listOf(
                    SettingsSectionUiModel(
                        title = "Account",
                        items = listOf(
                            SettingsItemUiModel(
                                id = "profile",
                                title = "Edit Profile",
                                iconRes = R.drawable.ic_launcher_foreground
                            ),
                            SettingsItemUiModel(
                                id = "premium",
                                title = "Premium",
                                iconRes = R.drawable.ic_launcher_foreground,
                                trailingValue = "Expired"
                            )
                        )
                    ),
                    SettingsSectionUiModel(
                        title = "Support",
                        items = listOf(
                            SettingsItemUiModel(
                                id = "contact",
                                title = "Contact Us",
                                iconRes = R.drawable.ic_launcher_foreground
                            ),
                            SettingsItemUiModel(
                                id = "about",
                                title = "About",
                                iconRes = R.drawable.ic_launcher_foreground
                            )
                        )
                    )
                )
            ),
            onBackClick = {},
            onItemClick = {},
            onUpgradePremiumClick = {}
        )
    }
}
