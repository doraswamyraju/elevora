package com.elevora.app.core.designsystem.colors

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Elevora Signature Midnight Navy & Champagne Gold Color System
 */
object ElevoraColors {
    // Core Backgrounds
    val DeepNavy = Color(0xFF080C16)
    val MidnightSurface = Color(0xFF0B132B)
    val DarkVoid = Color(0xFF050810)

    // Elevated Glassmorphism Surfaces
    val SlateCard = Color(0xFF131C31)
    val SlateCardGlass = Color(0xCC18233C)
    val CardStroke = Color(0xFF24304D)
    val CardStrokeSubtle = Color(0x20FFFFFF)

    // Signature Champagne Gold Palette
    val GoldPrimary = Color(0xFFF39C12)
    val GoldAccent = Color(0xFFF1C40F)
    val GoldLight = Color(0xFFFBD07A)
    val GoldAmber = Color(0xFFE5A638)
    val GoldGlow = Color(0x40F39C12)
    val GoldSubtle = Color(0x1AF39C12)

    // Typography & Content
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFF94A3B8)
    val TextMuted = Color(0xFF64748B)
    val TextGold = Color(0xFFE8BE6B)

    // Status & Badges
    val SuccessGreen = Color(0xFF22C55E)
    val SuccessGreenSubtle = Color(0x2022C55E)
    val AlertAmber = Color(0xFFF59E0B)

    // Category Accents
    val PurpleCategory = Color(0xFF8E44AD)
    val CyanCategory = Color(0xFF00A8B5)
    val EmeraldCategory = Color(0xFF27AE60)
    val CoralCategory = Color(0xFFE67E22)

    // Gradients
    val GoldButtonGradient = Brush.horizontalGradient(
        colors = listOf(GoldAmber, GoldLight)
    )

    val GoldRadialGlow = Brush.radialGradient(
        colors = listOf(GoldGlow, Color.Transparent)
    )

    val CardGlassGradient = Brush.verticalGradient(
        colors = listOf(Color(0xEE18233C), Color(0xFA131C31))
    )

    val BackgroundAtmosphere = Brush.verticalGradient(
        colors = listOf(Color(0xFF0D1836), DeepNavy, DarkVoid)
    )
}

