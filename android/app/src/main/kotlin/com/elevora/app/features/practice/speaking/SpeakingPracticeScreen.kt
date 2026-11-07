package com.elevora.app.features.practice.speaking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.features.practice.speaking.components.RecordAudioButton
import com.elevora.app.features.practice.speaking.components.RolePlayToggle
import com.elevora.app.features.practice.speaking.components.SpeakingMode

/**
 * Speaking Practice Lab Screen with Native Audio Recording trigger
 */
@Composable
fun SpeakingPracticeScreen(
    modifier: Modifier = Modifier
) {
    var selectedMode by remember { mutableStateOf(SpeakingMode.ROLE_PLAY) }
    var isRecording by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        RolePlayToggle(
            selectedMode = selectedMode,
            onModeSelected = { selectedMode = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 16.dp) {
            Column {
                Text(
                    text = "Professional Introductions",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Manager and employee scenario. Explain the situation, ask at least three questions, and agree on a next step.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 16.dp) {
            Column {
                Text(
                    text = "Guidelines",
                    color = ElevoraColors.GoldPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                GuidelineItem("Speak for 2-5 minutes.")
                GuidelineItem("Use the vocabulary from the lesson.")
                GuidelineItem("Ask at least three questions.")
                GuidelineItem("Agree on a next step.")
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        RecordAudioButton(
            isRecording = isRecording,
            onToggleRecord = { isRecording = !isRecording },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun GuidelineItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 3.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = ElevoraColors.GoldPrimary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "  $text",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp
        )
    }
}

