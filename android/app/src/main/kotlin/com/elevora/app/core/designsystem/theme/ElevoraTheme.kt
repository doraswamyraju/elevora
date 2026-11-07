package com.elevora.app.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.typography.ElevoraTypography

private val ElevoraColorScheme = darkColorScheme(
    primary = ElevoraColors.GoldPrimary,
    onPrimary = ElevoraColors.DarkVoid,
    primaryContainer = ElevoraColors.GoldSubtle,
    onPrimaryContainer = ElevoraColors.GoldLight,
    secondary = ElevoraColors.GoldAccent,
    onSecondary = ElevoraColors.DarkVoid,
    background = ElevoraColors.DeepNavy,
    onBackground = ElevoraColors.TextPrimary,
    surface = ElevoraColors.MidnightSurface,
    onSurface = ElevoraColors.TextPrimary,
    surfaceVariant = ElevoraColors.SlateCard,
    onSurfaceVariant = ElevoraColors.TextSecondary,
    outline = ElevoraColors.CardStroke
)

@Composable
fun ElevoraTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ElevoraColorScheme,
        typography = ElevoraTypography,
        content = content
    )
}

