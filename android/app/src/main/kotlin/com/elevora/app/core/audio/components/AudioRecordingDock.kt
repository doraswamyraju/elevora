package com.elevora.app.core.audio.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import java.io.File

/**
 * Luxury Audio Recording & Playback Dock for Learner Speaking Practice
 */
@Composable
fun AudioRecordingDock(
    isRecording: Boolean,
    isPlaying: Boolean,
    recordedFile: File?,
    onRecordToggle: () -> Unit,
    onPlayToggle: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isRecording) 1.25f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(700),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val micBgColor by animateColorAsState(
        targetValue = if (isRecording) Color(0xFFDC2626) else ElevoraColors.GoldPrimary,
        label = "micBgColor"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ElevoraColors.SlateCard)
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(20.dp))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when {
                isRecording -> "Recording in progress... Speak clearly"
                recordedFile != null -> "Recording captured! Listen to review"
                else -> "Tap the microphone to record your practice"
            },
            color = if (isRecording) Color(0xFFFCA5A5) else ElevoraColors.TextSecondary,
            fontSize = 12.sp,
            fontWeight = if (isRecording) FontWeight.Bold else FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Re-record / Clear button (only if audio file exists)
            if (recordedFile != null && !isRecording) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF16233C))
                        .border(1.dp, ElevoraColors.CardStroke, CircleShape)
                        .clickable(onClick = onReset),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Re-record",
                        tint = ElevoraColors.TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))
            }

            // Main Record / Stop Button with animated glowing pulse
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .scale(if (isRecording) pulseScale else 1f)
                    .clip(CircleShape)
                    .background(micBgColor)
                    .clickable(onClick = onRecordToggle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isRecording) Icons.Default.Stop else Icons.Default.Mic,
                    contentDescription = if (isRecording) "Stop Recording" else "Record Audio",
                    tint = if (isRecording) Color.White else ElevoraColors.DeepNavy,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Playback Button (only if recorded file exists and not currently recording)
            if (recordedFile != null && !isRecording) {
                Spacer(modifier = Modifier.width(20.dp))

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (isPlaying) ElevoraColors.EmeraldCategory else ElevoraColors.EmeraldCategory.copy(alpha = 0.2f))
                        .border(1.dp, ElevoraColors.EmeraldCategory, CircleShape)
                        .clickable(onClick = onPlayToggle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Stop else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Stop Playback" else "Play Recording",
                        tint = if (isPlaying) Color.White else ElevoraColors.EmeraldCategory,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

