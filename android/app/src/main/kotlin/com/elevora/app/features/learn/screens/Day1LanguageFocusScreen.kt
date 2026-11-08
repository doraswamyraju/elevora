package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
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
import com.elevora.app.features.learn.model.Day1Curriculum

@Composable
fun Day1LanguageFocusScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val curriculum = Day1Curriculum()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.SlateCard)
                    .border(1.dp, ElevoraColors.CardStroke, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ElevoraColors.TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = "Step 03 • Core Structure",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Language Focus & Say It",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Useful Language Box
            item {
                ElevoraGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 16.dp
                ) {
                    Column {
                        Text(
                            text = "USEFUL LANGUAGE RULES",
                            color = ElevoraColors.GoldPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        curriculum.languageFocusRules.forEach { rule ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(text = "✔ ", color = ElevoraColors.GoldPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Text(text = rule, color = ElevoraColors.TextPrimary, fontSize = 12.sp, lineHeight = 17.sp)
                            }
                        }
                    }
                }
            }

            // Say It Phrases Header
            item {
                Text(
                    text = "SAY IT — 6 KEY PHRASES",
                    color = ElevoraColors.TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            items(curriculum.sayItPhrases) { phrase ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(ElevoraColors.MidnightSurface)
                        .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(14.dp))
                        .clickable { /* Audio playback */ }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = phrase,
                            color = ElevoraColors.TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Listen",
                            tint = ElevoraColors.GoldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        ElevoraPrimaryButton(
            text = "Next: Controlled Practice (Exercises)",
            onClick = onContinueClick
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

