package com.elevora.app.features.practice.speaking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

@Composable
fun RecordAudioButton(
    isRecording: Boolean,
    onToggleRecord: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(
                    if (isRecording) androidx.compose.ui.graphics.SolidColor(ElevoraColors.AlertAmber)
                    else ElevoraColors.GoldButtonGradient
                )
                .border(4.dp, ElevoraColors.GoldGlow, CircleShape)
                .clickable(onClick = onToggleRecord),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isRecording) Icons.Filled.Stop else Icons.Filled.Mic,
                contentDescription = "Microphone",
                tint = ElevoraColors.DarkVoid,
                modifier = Modifier.size(34.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (isRecording) "Recording... Tap to Stop" else "Tap to Start Recording",
            color = ElevoraColors.TextPrimary,
            fontSize = 13.sp
        )
        Text(
            text = "Maximum 5 minutes",
            color = ElevoraColors.TextMuted,
            fontSize = 11.sp
        )
    }
}

