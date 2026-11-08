package com.elevora.app.features.practice.speaking

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.elevora.app.core.audio.ElevoraAudioRecorder
import com.elevora.app.core.audio.components.AudioRecordingDock
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.features.practice.speaking.components.RolePlayToggle
import com.elevora.app.features.practice.speaking.components.SpeakingMode
import java.io.File

/**
 * Speaking Practice Lab with Live Native Audio Recording & Playback Dock
 */
@Composable
fun SpeakingPracticeScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedMode by remember { mutableStateOf(SpeakingMode.ROLE_PLAY) }
    
    // Audio engine state
    val audioRecorder = remember { ElevoraAudioRecorder(context) }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var recordedAudioFile by remember { mutableStateOf<File?>(null) }
    var hasRecordPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasRecordPermission = isGranted
        if (isGranted) {
            recordedAudioFile = audioRecorder.startRecording("roleplay_speech")
            isRecording = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            audioRecorder.release()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // Mode Toggle: Free Practice vs Role-Play
        RolePlayToggle(
            selectedMode = selectedMode,
            onModeSelected = { selectedMode = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Scenario Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            ElevoraColors.SlateCard,
                            Color(0xFF101B32)
                        )
                    )
                )
                .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(22.dp))
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "MODULE 1 • SCENARIO 1",
                            color = ElevoraColors.GoldLight,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.6.sp
                        )
                    }

                    Text(
                        text = "~2 min target",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Executive Self-Introduction & Team Sync",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Introduce yourself to senior management. Clearly state your department, your 2 main responsibilities, and your team's top priority this sprint.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Suggested Model Phrasing Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFF0C1426))
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "TARGET PHRASES TO INCLUDE:",
                    color = ElevoraColors.GoldLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )

                TargetCheckRow("Good morning! My name is Priya, and I oversee Operations.")
                TargetCheckRow("My primary responsibility is ensuring cross-functional delivery.")
                TargetCheckRow("Our immediate focus this week is streamlining client onboarding.")
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Native Audio Recording Dock
        AudioRecordingDock(
            isRecording = isRecording,
            isPlaying = isPlaying,
            recordedFile = recordedAudioFile,
            onRecordToggle = {
                if (isRecording) {
                    recordedAudioFile = audioRecorder.stopRecording()
                    isRecording = false
                } else {
                    if (hasRecordPermission) {
                        recordedAudioFile = audioRecorder.startRecording("roleplay_speech")
                        isRecording = true
                    } else {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                }
            },
            onPlayToggle = {
                if (isPlaying) {
                    audioRecorder.stopPlayback()
                    isPlaying = false
                } else {
                    isPlaying = true
                    audioRecorder.playRecording(recordedAudioFile) {
                        isPlaying = false
                    }
                }
            },
            onReset = {
                audioRecorder.stopPlayback()
                recordedAudioFile = null
                isPlaying = false
                isRecording = false
            }
        )

        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom bar
    }
}

@Composable
private fun TargetCheckRow(phrase: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = ElevoraColors.GoldPrimary,
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = phrase,
            color = ElevoraColors.TextPrimary.copy(alpha = 0.9f),
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
    }
}
