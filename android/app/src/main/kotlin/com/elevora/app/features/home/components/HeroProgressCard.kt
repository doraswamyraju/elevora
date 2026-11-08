package com.elevora.app.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.progress.ElevoraCircularGauge

/**
 * Luxury Hero Course Card with artwork backdrop, ambient gold highlights,
 * circular progress dial, and high-impact CTA button
 */
@Composable
fun HeroProgressCard(
    moduleTag: String = "MODULE 1 • FOUNDATIONS",
    moduleTitle: String = "Managerial English Foundations",
    weekInfo: String = "Week 1 of 4",
    dayProgressPercent: Float = 0.85f,
    activeDayTitle: String = "Day 1: Professional Introductions",
    onContinueClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F1B33),
                        Color(0xFF142444),
                        Color(0xFF0B1426)
                    )
                )
            )
            .border(
                1.5.dp,
                Brush.linearGradient(
                    colors = listOf(
                        ElevoraColors.GoldPrimary.copy(alpha = 0.6f),
                        ElevoraColors.CardStroke,
                        ElevoraColors.GoldPrimary.copy(alpha = 0.2f)
                    )
                ),
                RoundedCornerShape(26.dp)
            )
    ) {
        // Ambient soft golden spotlight glow in background
        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            ElevoraColors.GoldPrimary.copy(alpha = 0.18f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header Row: Pill Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(ElevoraColors.GoldPrimary)
                    )
                    Text(
                        text = moduleTag,
                        color = ElevoraColors.GoldPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF1E2E50))
                        .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = weekInfo,
                        color = ElevoraColors.GoldLight,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Main Title & Circular Progress Dial
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = moduleTitle,
                        color = ElevoraColors.TextPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 25.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = activeDayTitle,
                        color = ElevoraColors.TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Progress Dial with glowing backdrop
                Box(contentAlignment = Alignment.Center) {
                    ElevoraCircularGauge(
                        percentage = dayProgressPercent,
                        size = 68.dp,
                        strokeWidth = 7.dp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Embedded Visual Graphic Banner (Module 1 / Professional Stepping Scene)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.module1_banner),
                    contentDescription = "Module 1 Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                // Gradient scrim overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xEE0B1426),
                                    Color(0x880B1426),
                                    Color(0x330B1426)
                                )
                            )
                        )
                )

                // Text overlay badge inside banner
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 14.dp)
                ) {
                    Text(
                        text = "EXECUTIVE WORKPLACE ENGLISH",
                        color = ElevoraColors.GoldLight,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Day 1: Elevator Pitch & Intro",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Luxury Button: "Continue Day 1 - Professional Introductions"
            Button(
                onClick = onContinueClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ElevoraColors.GoldPrimary,
                    contentColor = ElevoraColors.DeepNavy
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(ElevoraColors.DeepNavy),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = ElevoraColors.GoldPrimary,
                        modifier = Modifier.size(15.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Continue Day 1 - Role-Play",
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}
