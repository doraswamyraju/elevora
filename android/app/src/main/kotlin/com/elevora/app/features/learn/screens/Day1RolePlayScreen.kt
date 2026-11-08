package com.elevora.app.features.learn.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.features.learn.model.Day1Curriculum

enum class RolePlayMode {
    WITHOUT_AI,
    WITH_AI
}

@Composable
fun Day1RolePlayScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val curriculum = Day1Curriculum()
    var mode by remember { mutableStateOf(RolePlayMode.WITHOUT_AI) }

    // Recording & AI state
    var isRecording by remember { mutableStateOf(false) }
    var spokenResponseText by remember {
        mutableStateOf("Good morning! My name is Priya, and I oversee the Operations department. My main responsibility is ensuring on-time project execution.")
    }
    var apiKeyInput by remember { mutableStateOf("") }
    var aiReply by remember { mutableStateOf<String?>(null) }
    var aiFluencyScore by remember { mutableStateOf("4.5 / 5.0") }
    var tokensUsed by remember { mutableStateOf("230 tokens (~$0.000035)") }
    var isEvaluating by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
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
                    text = "Step 05 • Role-Play Area",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Manager & Colleague Dialogue",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        // Mode Switcher: "Without AI (Drill)" vs "With AI (Gemini Live)"
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.GoldPrimary else ElevoraColors.MidnightSurface)
                    .border(
                        1.dp,
                        if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { mode = RolePlayMode.WITHOUT_AI }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.RecordVoiceOver,
                        contentDescription = null,
                        tint = if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.DeepNavy else ElevoraColors.TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Without AI (Drill)",
                        color = if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.DeepNavy else ElevoraColors.TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (mode == RolePlayMode.WITH_AI) ElevoraColors.GoldPrimary else ElevoraColors.MidnightSurface)
                    .border(
                        1.dp,
                        if (mode == RolePlayMode.WITH_AI) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { mode = RolePlayMode.WITH_AI }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        tint = if (mode == RolePlayMode.WITH_AI) ElevoraColors.DeepNavy else ElevoraColors.GoldPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "With AI (Google GenAI)",
                        color = if (mode == RolePlayMode.WITH_AI) ElevoraColors.DeepNavy else ElevoraColors.TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Realistic Corporate Dialogue Banner Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.roleplay_meeting),
                contentDescription = "Role-Play Scenario",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ElevoraColors.DeepNavy.copy(alpha = 0.65f))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                Text(
                    text = "OFFICIAL SCENARIO (FROM TRAINER GUIDE)",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Manager & Colleague Introduction",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Scenario Task Rules
        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 14.dp) {
            Column {
                Text(
                    text = "Your Role as Manager:",
                    color = ElevoraColors.GoldPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "1. Introduce yourself & department.\n2. Ask at least 3 follow-up questions.\n3. Agree on the next step.",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // MODE CONTENT
        if (mode == RolePlayMode.WITHOUT_AI) {
            // Mode A: Self-guided Structured Drill
            Text(
                text = "Structured Turn-Taking Script:",
                color = ElevoraColors.TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                DialogueBubble(
                    speaker = "You (Manager)",
                    text = "Good morning! My name is Priya, and I oversee the Operations department.",
                    isUser = true
                )
                DialogueBubble(
                    speaker = "Colleague",
                    text = "Good morning Priya, great to meet you! I recently joined the Logistics team.",
                    isUser = false
                )
                DialogueBubble(
                    speaker = "You (Manager)",
                    text = "Could you please clarify your main responsibilities for this quarter?",
                    isUser = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Microphone recording container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(ElevoraColors.MidnightSurface)
                    .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp))
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isRecording) "Recording your 2–5 min introduction..." else "Tap mic to record your response",
                        color = if (isRecording) ElevoraColors.AlertAmber else ElevoraColors.TextSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(ElevoraColors.GoldPrimary)
                            .clickable { isRecording = !isRecording },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Record",
                            tint = ElevoraColors.DeepNavy,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        } else {
            // Mode B: With AI (Google GenAI Gemini integration)
            ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 14.dp) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            tint = ElevoraColors.GoldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Google AI Studio Practice Coach",
                            color = ElevoraColors.GoldPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Speak or enter your introduction. Gemini 2.5 Flash acts as your colleague, evaluates your hesitation, and calculates token usage.",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = spokenResponseText,
                        onValueChange = { spokenResponseText = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Your Managerial Response") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElevoraColors.GoldPrimary,
                            unfocusedBorderColor = ElevoraColors.CardStroke,
                            focusedTextColor = ElevoraColors.TextPrimary,
                            unfocusedTextColor = ElevoraColors.TextPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            isEvaluating = true
                            aiReply = "Nice to meet you Priya! As a logistics lead, could you please clarify what our team's main objective should be this week?"
                            isEvaluating = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ElevoraColors.GoldPrimary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Send to AI Coach", color = ElevoraColors.DeepNavy, fontWeight = FontWeight.Bold)
                    }
                }
            }

            if (aiReply != null) {
                Spacer(modifier = Modifier.height(12.dp))

                // AI Response & Telemetry Widget
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(ElevoraColors.SlateCard)
                        .border(1.2.dp, ElevoraColors.GoldPrimary, RoundedCornerShape(14.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "AI Colleague Response:",
                            color = ElevoraColors.GoldPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "\"$aiReply\"",
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = ElevoraColors.CardStroke)
                        Spacer(modifier = Modifier.height(10.dp))

                        // Token Telemetry
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = "Fluency Score", color = ElevoraColors.TextMuted, fontSize = 10.sp)
                                Text(text = aiFluencyScore, color = ElevoraColors.GoldPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text(text = "Token Usage", color = ElevoraColors.TextMuted, fontSize = 10.sp)
                                Text(text = tokensUsed, color = ElevoraColors.TextPrimary, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ElevoraPrimaryButton(
            text = "Next: Check Your Understanding (Quiz)",
            onClick = onContinueClick
        )

        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
private fun DialogueBubble(speaker: String, text: String, isUser: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isUser) ElevoraColors.MidnightSurface else ElevoraColors.SlateCardGlass)
            .border(
                1.dp,
                if (isUser) ElevoraColors.GoldPrimary.copy(alpha = 0.5f) else ElevoraColors.CardStroke,
                RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = speaker,
                color = if (isUser) ElevoraColors.GoldPrimary else ElevoraColors.TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = text,
                color = ElevoraColors.TextPrimary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}

