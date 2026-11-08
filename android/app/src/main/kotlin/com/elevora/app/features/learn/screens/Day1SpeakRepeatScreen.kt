package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.learn.model.Day1FullContent

/**
 * Screen matching Screenshot 7: Practice – Speak & Repeat with Soundwave & Recording
 */
@Composable
fun Day1SpeakRepeatScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val content = Day1FullContent()
    var isRecording by remember { mutableStateOf(false) }
    var currentSentenceIndex by remember { mutableStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(38.dp)
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
            Text(
                text = "Practice",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Progress bar indicator (5 segments)
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            (1..5).forEach { idx ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (idx <= currentSentenceIndex) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke)
                )
            }
        }

        Text(
            text = "Speak & Repeat",
            color = ElevoraColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Listen to the sentence and repeat it clearly. Tap the mic and speak.",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Target Sentence Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(ElevoraColors.MidnightSurface)
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Speaker audio button
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(ElevoraColors.GoldSubtle)
                        .border(1.dp, ElevoraColors.GoldPrimary, CircleShape)
                        .clickable { /* Play audio */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.VolumeUp,
                        contentDescription = "Listen",
                        tint = ElevoraColors.GoldPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = content.speakRepeatSentence,
                    color = ElevoraColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Simulated Soundwave
        Row(
            modifier = Modifier.height(40.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val waveHeights = listOf(8, 14, 22, 18, 30, 24, 12, 28, 36, 20, 16, 32, 26, 14, 22, 10)
            waveHeights.forEach { h ->
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(if (isRecording) (h * 1.2).dp else (h * 0.7).dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (isRecording) ElevoraColors.GoldPrimary else ElevoraColors.GoldAmber.copy(alpha = 0.6f))
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Recording Controls: Reset | Big Mic | Play
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* Reset */ },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.SlateCard)
                    .border(1.dp, ElevoraColors.CardStroke, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Restart",
                    tint = ElevoraColors.TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Big Yellow Mic Button
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.GoldPrimary)
                    .clickable { isRecording = !isRecording },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "Record",
                    tint = ElevoraColors.DeepNavy,
                    modifier = Modifier.size(34.dp)
                )
            }

            IconButton(
                onClick = { /* Play recorded */ },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.SlateCard)
                    .border(1.dp, ElevoraColors.CardStroke, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play back",
                    tint = ElevoraColors.TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "$currentSentenceIndex of 5",
            color = ElevoraColors.TextSecondary,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        ElevoraPrimaryButton(
            text = "Continue to Quick Activity",
            onClick = onContinueClick
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}
