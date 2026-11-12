package com.elevora.app.core.designsystem.colors

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Elevora Executive Oxford Light Mode Design System:
 * Premium academic & managerial credibility: Oxford Blue, Warm Amber,
 * crisp paper whites, and deep ink slate typography for effortless long-session reading.
 */
object ElevoraColors {
    // Core Backgrounds (Paper Alabaster / Crisp Canvas)
    val DeepNavy = Color(0xFFF8FAFC)        // Main screen background: Soft off-white / Paper Alabaster
    val MidnightSurface = Color(0xFFFFFFFF) // Primary elevated card surface: Pure White
    val DarkVoid = Color(0xFFF1F5F9)        // Secondary subtle container: Soft Slate

    // Elevated Card Surfaces & Dividers
    val SlateCard = Color(0xFFFFFFFF)       // Primary Card: Crisp White
    val SlateCardGlass = Color(0xFFFFFFFF)  // Glass Card: Pure White
    val CardStroke = Color(0xFFE2E8F0)      // Border / Dividers: Subtle Slate
    val CardStrokeSubtle = Color(0xFFF1F5F9)// Subtle inner border

    // Oxford Blue Primary & Accents
    val GoldPrimary = Color(0xFF1E3A8A)     // Royal Oxford Blue (Primary brand & action)
    val GoldAccent = Color(0xFF2563EB)      // Vibrant Royal Blue
    val GoldLight = Color(0xFF3B82F6)       // Interactive Blue
    val GoldAmber = Color(0xFFD97706)       // Warm Cognac Amber (Milestones, flame, badges)
    val GoldGlow = Color(0x1A1E3A8A)        // Soft blue ambient shadow/glow
    val GoldSubtle = Color(0xFFEFF6FF)      // Ice Blue tint for active pill selections

    // Typography & Content (Deep Ink Slate for maximum legibility & zero eye strain)
    val TextPrimary = Color(0xFF0F172A)     // Deep Ink Slate (Crisp contrast)
    val TextSecondary = Color(0xFF475569)   // Muted Charcoal (Subtitles, descriptions)
    val TextMuted = Color(0xFF64748B)       // Slate Gray (Placeholders, inactive tabs)
    val TextGold = Color(0xFFB45309)        // Amber accent text

    // Status & Badges
    val SuccessGreen = Color(0xFF059669)    // Emerald Green (Correct, Live AI Connected)
    val SuccessGreenSubtle = Color(0xFFECFDF5)
    val AlertAmber = Color(0xFFD97706)      // Warm Amber (Alerts, warnings)

    // Category Accents
    val PurpleCategory = Color(0xFF6366F1)  // Indigo
    val CyanCategory = Color(0xFF0284C7)    // Sky Blue
    val EmeraldCategory = Color(0xFF059669) // Emerald
    val CoralCategory = Color(0xFFEA580C)   // Terracotta / Coral

    // Gradients
    val GoldButtonGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF1E3A8A), Color(0xFF2563EB))
    )

    val GoldRadialGlow = Brush.radialGradient(
        colors = listOf(GoldGlow, Color.Transparent)
    )

    val CardGlassGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF8FAFC))
    )

    val BackgroundAtmosphere = Brush.verticalGradient(
        colors = listOf(Color(0xFFF8FAFC), Color(0xFFF1F5F9))
    )
}
