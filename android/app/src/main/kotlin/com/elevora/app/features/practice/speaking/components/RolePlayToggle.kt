package com.elevora.app.features.practice.speaking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

enum class SpeakingMode {
    ROLE_PLAY, SPEAKING_TASK
}

@Composable
fun RolePlayToggle(
    selectedMode: SpeakingMode,
    onModeSelected: (SpeakingMode) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(24.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(shape)
            .background(ElevoraColors.SlateCard)
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(shape)
                .background(
                    if (selectedMode == SpeakingMode.ROLE_PLAY) ElevoraColors.GoldButtonGradient
                    else androidx.compose.ui.graphics.SolidColor(androidx.compose.ui.graphics.Color.Transparent)
                )
                .clickable { onModeSelected(SpeakingMode.ROLE_PLAY) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Role-play",
                color = if (selectedMode == SpeakingMode.ROLE_PLAY) ElevoraColors.DarkVoid else ElevoraColors.TextSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(shape)
                .background(
                    if (selectedMode == SpeakingMode.SPEAKING_TASK) ElevoraColors.GoldButtonGradient
                    else androidx.compose.ui.graphics.SolidColor(androidx.compose.ui.graphics.Color.Transparent)
                )
                .clickable { onModeSelected(SpeakingMode.SPEAKING_TASK) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Speaking Task",
                color = if (selectedMode == SpeakingMode.SPEAKING_TASK) ElevoraColors.DarkVoid else ElevoraColors.TextSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }
    }
}

