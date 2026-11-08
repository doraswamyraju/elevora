package com.elevora.app.features.learn.screens

import android.Manifest
import android.content.pm.PackageManager
import android.speech.tts.TextToSpeech
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.core.network.ElevoraApiClient
import com.elevora.app.core.speech.ElevoraSpeechRecognizer
import com.elevora.app.features.learn.model.Day1Curriculum
import kotlinx.coroutines.launch
import java.util.Locale

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
    val context = LocalContext.current
    val curriculum = Day1Curriculum()
    var mode by remember { mutableStateOf(RolePlayMode.WITH_AI) }

    // Voice State
    var isListening by remember { mutableStateOf(false) }
    var spokenVoiceText by remember { mutableStateOf("") }
    var voiceErrorMessage by remember { mutableStateOf<String?>(null) }
    var aiReply by remember { mutableStateOf<String?>(null) }
    var aiFluencyScore by remember { mutableStateOf("4.5 / 5.0") }
    var improvedVersion by remember { mutableStateOf<String?>(null) }
    var isEvaluating by remember { mutableStateOf(false) }
    var isSpeakingTts by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    // 1. Text-To-Speech for AI spoken responses
    var textToSpeech by remember { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(Unit) {
        var ttsInstance: TextToSpeech? = null
        ttsInstance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                ttsInstance?.language = Locale.UK
            }
        }
        textToSpeech = ttsInstance
        onDispose {
            ttsInstance.stop()
            ttsInstance.shutdown()
        }
    }

    // Function to evaluate speech and speak back
    fun triggerAiEvaluation(speechText: String) {
        if (speechText.isBlank()) return
        // Stop any currently playing TTS before processing new speech
        textToSpeech?.stop()
        isEvaluating = true
        coroutineScope.launch {
            val eval = ElevoraApiClient.sendSpeakingPractice(
                userSpokenText = speechText,
                targetScenario = "Module 1, Lesson 1: Professional Self-Introduction",
                apiKey = null
            )
            aiReply = eval.rolePlayReply
            aiFluencyScore = "${eval.fluencyScore} / 5.0"
            improvedVersion = eval.improvedAlternative
            isEvaluating = false

            // Automatically speak the AI response out loud for real conversation
            textToSpeech?.speak(eval.rolePlayReply, TextToSpeech.QUEUE_FLUSH, null, "ai_reply")
        }
    }

    // 2. Native Speech Recognizer
    var speechRecognizer by remember { mutableStateOf<ElevoraSpeechRecognizer?>(null) }
    DisposableEffect(Unit) {
        val recognizer = ElevoraSpeechRecognizer(
            context = context,
            onFinalResult = { completeSentence ->
                spokenVoiceText = completeSentence
                voiceErrorMessage = null
                triggerAiEvaluation(completeSentence)
            },
            onPartial = { liveSpeechDraft ->
                spokenVoiceText = liveSpeechDraft // Update screen text in real-time without interrupting
                voiceErrorMessage = null
            },
            onError = { errMsg ->
                isListening = false
                voiceErrorMessage = errMsg
            },
            onListeningStateChanged = { listening ->
                isListening = listening
                if (listening) voiceErrorMessage = null
            }
        )
        speechRecognizer = recognizer
        onDispose {
            recognizer.destroy()
        }
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            speechRecognizer?.startListening()
        }
    }

    fun startVoiceCapture() {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            speechRecognizer?.startListening()
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    // Pulse animation for recording
    val infiniteTransition = rememberInfiniteTransition(label = "voicePulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isListening) 1.28f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(650),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
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
                    tint = ElevoraColors.GoldPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Step 05 • Role-Play Area",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Executive Workplace Dialogue",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        // Mode Switcher Pill (Interactive Drill vs AI Speaking Partner)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(ElevoraColors.SlateCard)
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(14.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                .background(if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.GoldPrimary else Color.Transparent)
                    .clickable { mode = RolePlayMode.WITHOUT_AI }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Guided Drill (Reference)",
                    color = if (mode == RolePlayMode.WITHOUT_AI) ElevoraColors.DeepNavy else ElevoraColors.TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (mode == RolePlayMode.WITH_AI) ElevoraColors.GoldPrimary else Color.Transparent)
                    .clickable { mode = RolePlayMode.WITH_AI }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🎙️ Live Voice Coach",
                    color = if (mode == RolePlayMode.WITH_AI) ElevoraColors.DeepNavy else ElevoraColors.TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Scenario Card with photo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.roleplay_meeting),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(130.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xEE080C16))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                Text(
                    text = "OFFICIAL WORKPLACE SCENARIO",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Colleague & Manager First Introduction",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (mode == RolePlayMode.WITHOUT_AI) {
            // Mode A: Guided Reference Dialogue
            ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 16.dp) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Model Dialogue Script",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    DialogueBubble(
                        speaker = "Colleague (Alex):",
                        text = "Good morning! Welcome to the team. What department do you lead?",
                        isUser = false
                    )
                    DialogueBubble(
                        speaker = "Your Line (Learner):",
                        text = "Good morning, Alex. My name is Priya, and I oversee the Operations department.",
                        isUser = true
                    )
                    DialogueBubble(
                        speaker = "Colleague (Alex):",
                        text = "Great to meet you Priya! What is your main priority for this week?",
                        isUser = false
                    )
                    DialogueBubble(
                        speaker = "Your Line (Learner):",
                        text = "My main responsibility is ensuring on-time delivery across all active client sprints.",
                        isUser = true
                    )
                }
            }
        } else {
            // Mode B: Interactive Voice-First AI Coach
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                ElevoraColors.SlateCard,
                                Color(0xFF0F1A32)
                            )
                        )
                    )
                    .border(1.5.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Live Voice Speaking Assessment",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Tap the microphone, introduce yourself, and the coach will converse with you via voice.",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
                    )

                    // Big Animated Voice Capture Mic Button
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .scale(if (isListening) pulseScale else 1f)
                            .clip(CircleShape)
                            .background(if (isListening) Color(0xFFDC2626) else ElevoraColors.GoldPrimary)
                            .clickable {
                                if (isListening) {
                                    speechRecognizer?.stopListening()
                                } else {
                                    startVoiceCapture()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isListening) Icons.Default.Stop else Icons.Default.Mic,
                            contentDescription = "Speak to AI",
                            tint = if (isListening) Color.White else ElevoraColors.DeepNavy,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = when {
                            voiceErrorMessage != null -> voiceErrorMessage!!
                            isListening -> "Listening to your voice... Speak now"
                            isEvaluating -> "Assessing your speech & fluency with live AI..."
                            spokenVoiceText.isNotBlank() -> "Tap mic to speak again"
                            else -> "Tap microphone to speak"
                        },
                        color = when {
                            voiceErrorMessage != null -> ElevoraColors.AlertAmber
                            isListening -> Color(0xFFFCA5A5)
                            else -> ElevoraColors.GoldLight
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // What the learner spoke
                    if (spokenVoiceText.isNotBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF0C1424))
                                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = "YOU SAID:",
                                    color = ElevoraColors.GoldPrimary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "\"$spokenVoiceText\"",
                                    color = ElevoraColors.TextPrimary,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }

                    if (isEvaluating) {
                        Spacer(modifier = Modifier.height(14.dp))
                        CircularProgressIndicator(
                            color = ElevoraColors.GoldPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // AI Verbal Reply & Assessment Breakdown
            if (aiReply != null) {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF0E1A34))
                        .border(1.2.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Colleague Spoken Response",
                                    color = ElevoraColors.TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                IconButton(
                                    onClick = {
                                        textToSpeech?.speak(aiReply, TextToSpeech.QUEUE_FLUSH, null, "replay")
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = "Replay audio",
                                        tint = ElevoraColors.GoldPrimary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "Fluency: $aiFluencyScore",
                                    color = ElevoraColors.GoldPrimary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "\"$aiReply\"",
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )

                        if (improvedVersion != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = ElevoraColors.CardStroke)
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "EXECUTIVE FLUENCY IMPROVEMENT:",
                                color = ElevoraColors.GoldLight,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.8.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "\"$improvedVersion\"",
                                color = ElevoraColors.TextPrimary,
                                fontSize = 12.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ElevoraPrimaryButton(
            text = "Next: Check Your Understanding (Quiz) →",
            onClick = onContinueClick
        )

        Spacer(modifier = Modifier.height(20.dp))
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
