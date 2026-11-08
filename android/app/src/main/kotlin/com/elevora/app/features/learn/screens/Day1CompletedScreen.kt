package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard

@Composable
fun Day1CompletedScreen(
    onContinueToDay2: () -> Unit = {},
    onBackToWeek1: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var phraseUsed by remember { mutableStateOf("Let's look at the best solution.") }
    var mistakeAvoided by remember { mutableStateOf("Translating word-for-word from my native language.") }
    var confidenceRating by remember { mutableStateOf(5) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Gold Medal Badge Badge Container
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(ElevoraColors.GoldPrimary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Completed",
                tint = ElevoraColors.DeepNavy,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lesson 1 Completed!",
            color = ElevoraColors.TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Professional Introductions • Module 1 Week 1",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Stats Row: 7/7 Steps | 100% Score | +50 Points
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CompletionStatCard(modifier = Modifier.weight(1f), icon = "📚", value = "7 / 7", label = "Steps Done")
            CompletionStatCard(modifier = Modifier.weight(1f), icon = "🏆", value = "100%", label = "Accuracy")
            CompletionStatCard(modifier = Modifier.weight(1f), icon = "⭐", value = "+50", label = "XP Earned")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Lesson Reflection & Self-Audit (From Learner Book page 3)
        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 16.dp) {
            Column {
                Text(
                    text = "LESSON REFLECTION (AUDIT)",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "1. One phrase I will use this week:",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 11.sp
                )
                OutlinedTextField(
                    value = phraseUsed,
                    onValueChange = { phraseUsed = it },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElevoraColors.GoldPrimary,
                        unfocusedBorderColor = ElevoraColors.CardStroke,
                        focusedTextColor = ElevoraColors.TextPrimary,
                        unfocusedTextColor = ElevoraColors.TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "2. One mistake I want to avoid:",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 11.sp
                )
                OutlinedTextField(
                    value = mistakeAvoided,
                    onValueChange = { mistakeAvoided = it },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElevoraColors.GoldPrimary,
                        unfocusedBorderColor = ElevoraColors.CardStroke,
                        focusedTextColor = ElevoraColors.TextPrimary,
                        unfocusedTextColor = ElevoraColors.TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "3. Confidence today (1–5 scale):",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 11.sp
                )
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (1..5).forEach { star ->
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (star <= confidenceRating) ElevoraColors.GoldPrimary else ElevoraColors.SlateCard)
                                .clickable { confidenceRating = star },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$star",
                                color = if (star <= confidenceRating) ElevoraColors.DeepNavy else ElevoraColors.TextMuted,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ElevoraPrimaryButton(
            text = "Continue to Day 2: Job Roles →",
            onClick = onContinueToDay2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(ElevoraColors.SlateCard)
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(26.dp))
                .clickable(onClick = onBackToWeek1)
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "← Back to Week 1 Plan",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun CompletionStatCard(icon: String, value: String, label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(ElevoraColors.SlateCardGlass)
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(14.dp))
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = icon, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, color = ElevoraColors.GoldPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = label, color = ElevoraColors.TextSecondary, fontSize = 10.sp)
        }
    }
}
