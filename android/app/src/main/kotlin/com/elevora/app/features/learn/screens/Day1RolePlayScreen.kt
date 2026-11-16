package com.elevora.app.features.learn.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
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
import androidx.compose.material.icons.filled.Refresh
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
import com.elevora.app.core.network.ConversationMessage
import com.elevora.app.core.network.ElevoraApiClient
import com.elevora.app.core.speech.ElevoraSpeechRecognizer
import com.elevora.app.features.learn.model.Day1Curriculum
import kotlinx.coroutines.launch
import java.util.Locale

enum class RolePlayMode {
    WITHOUT_AI,
    WITH_AI
}

fun resolveLanguageDisplay(code: String?, text: String?): String {
    val cleanCode = code?.lowercase()?.trim()
    if (cleanCode == "te" || cleanCode == "telugu") return "🇮🇳 Telugu (తెలుగు)"
    if (cleanCode == "hi" || cleanCode == "hindi") return "🇮🇳 Hindi (हिंदी)"
    if (cleanCode == "ta" || cleanCode == "tamil") return "🇮🇳 Tamil (தமிழ்)"
    if (cleanCode == "kn" || cleanCode == "kannada") return "🇮🇳 Kannada (ಕನ್ನಡ)"
    if (cleanCode == "en" || cleanCode == "english") return "🇬🇧 English"

    if (text != null) {
        for (ch in text) {
            if (ch in '\u0C00'..'\u0C7F') return "🇮🇳 Telugu (తెలుగు)"
            if (ch in '\u0900'..'\u097F') return "🇮🇳 Hindi (हिंदी)"
            if (ch in '\u0B80'..'\u0BFF') return "🇮🇳 Tamil (தமிழ்)"
            if (ch in '\u0C80'..'\u0CFF') return "🇮🇳 Kannada (ಕನ್ನಡ)"
        }
    }
    return "🇬🇧 English"
}

fun resolveTtsLocale(code: String?, text: String?): Locale {
    val cleanCode = code?.lowercase()?.trim()
    if (cleanCode == "te" || cleanCode == "telugu") return Locale.forLanguageTag("te-IN")
    if (cleanCode == "hi" || cleanCode == "hindi") return Locale.forLanguageTag("hi-IN")
    if (cleanCode == "ta" || cleanCode == "tamil") return Locale.forLanguageTag("ta-IN")
    if (cleanCode == "kn" || cleanCode == "kannada") return Locale.forLanguageTag("kn-IN")
    if (cleanCode == "en" || cleanCode == "english") return Locale.forLanguageTag("en-IN")

    if (text != null) {
        for (ch in text) {
            if (ch in '\u0C00'..'\u0C7F') return Locale.forLanguageTag("te-IN")
            if (ch in '\u0900'..'\u097F') return Locale.forLanguageTag("hi-IN")
            if (ch in '\u0B80'..'\u0BFF') return Locale.forLanguageTag("ta-IN")
            if (ch in '\u0C80'..'\u0CFF') return Locale.forLanguageTag("kn-IN")
        }
    }
    return Locale.forLanguageTag("en-IN")
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

    // Live AI Connection State
    var isAiConnected by remember { mutableStateOf<Boolean?>(null) }
    var isCheckingConnection by remember { mutableStateOf(false) }

    // Hands-Free Continuous Session State
    var isSessionActive by remember { mutableStateOf(false) }
    var isAiSpeaking by remember { mutableStateOf(false) }
    var isSessionConcluding by remember { mutableStateOf(false) }
    var detectedLanguageName by remember { mutableStateOf("Auto-Detecting") }
    val mainHandler = remember { Handler(Looper.getMainLooper()) }

    // Multi-turn conversation turns for realistic conversational memory
    val conversationTurns = remember { mutableStateListOf<ConversationMessage>() }
    val initialPriyaGreeting = "Good morning! Welcome to the team. My name is Priya Sharma, and I oversee the Operations department. Which department are you joining, and what is your role?"

    // Voice State - strictly live, no dummy defaults
    var isListening by remember { mutableStateOf(false) }
    var spokenVoiceText by remember { mutableStateOf("") }
    var voiceErrorMessage by remember { mutableStateOf<String?>(null) }
    var aiReply by remember { mutableStateOf<String?>(null) }
    var aiFluencyScore by remember { mutableStateOf<String?>(null) }
    var aiAccuracyScore by remember { mutableStateOf<String?>(null) }
    var improvedVersion by remember { mutableStateOf<String?>(null) }
    var trainerObservation by remember { mutableStateOf<String?>(null) }
    var vocabularyUsed by remember { mutableStateOf<List<String>>(emptyList()) }
    var isEvaluating by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    fun verifyAiConnection() {
        isCheckingConnection = true
        coroutineScope.launch {
            val connected = ElevoraApiClient.checkAiConnection()
            isAiConnected = connected
            isCheckingConnection = false
            if (connected) {
                voiceErrorMessage = null
            } else if (voiceErrorMessage == null) {
                voiceErrorMessage = "AI Coach offline. Please verify internet connection."
            }
        }
    }

    LaunchedEffect(Unit) {
        verifyAiConnection()
    }

    // Forward declaration of capture starter
    var triggerVoiceCaptureAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    // 1. Text-To-Speech with UtteranceProgressListener for auto-loop
    var textToSpeech by remember { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(Unit) {
        var ttsInstance: TextToSpeech? = null
        ttsInstance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                try {
                    ttsInstance?.language = Locale.forLanguageTag("te-IN")
                } catch (_: Exception) {
                    ttsInstance?.language = Locale.UK
                }
            }
        }
        ttsInstance.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                isAiSpeaking = true
            }
            override fun onDone(utteranceId: String?) {
                isAiSpeaking = false
                // Auto-resume listening hands-free when AI finishes speaking!
                if (isSessionActive && !isSessionConcluding) {
                    mainHandler.post {
                        triggerVoiceCaptureAction?.invoke()
                    }
                }
            }
            override fun onError(utteranceId: String?) {
                isAiSpeaking = false
                if (isSessionActive && !isSessionConcluding) {
                    mainHandler.post {
                        triggerVoiceCaptureAction?.invoke()
                    }
                }
            }
        })
        textToSpeech = ttsInstance
        onDispose {
            ttsInstance.stop()
            ttsInstance.shutdown()
        }
    }

    // Speak AI response in the dynamically detected language
    fun speakAiResponse(text: String, detectedLang: String? = null) {
        if (text.isBlank()) return
        val targetLocale = resolveTtsLocale(detectedLang, text)
        try {
            val langResult = textToSpeech?.setLanguage(targetLocale)
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                textToSpeech?.setLanguage(Locale.forLanguageTag("en-IN"))
            }
        } catch (_: Exception) {
            textToSpeech?.language = Locale.UK
        }
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ai_reply_${System.currentTimeMillis()}")
    }

    // Function to evaluate speech and speak back via live AI
    fun triggerAiEvaluation(speechText: String) {
        if (speechText.isBlank()) return
        textToSpeech?.stop()
        isEvaluating = true
        voiceErrorMessage = null

        // Add learner's turn to conversation history
        conversationTurns.add(ConversationMessage("user", speechText))

        coroutineScope.launch {
            val eval = ElevoraApiClient.sendSpeakingPractice(
                userSpokenText = speechText,
                targetScenario = "Module 1, Lesson 1: Professional Self-Introduction",
                language = "auto",
                conversationHistory = conversationTurns.toList(),
                apiKey = null
            )
            isEvaluating = false

            if (eval.isSuccess && !eval.rolePlayReply.isNullOrBlank()) {
                isAiConnected = true
                aiReply = eval.rolePlayReply
                aiFluencyScore = eval.fluencyScore?.let { "$it / 5.0" }
                aiAccuracyScore = eval.accuracyScore?.let { "$it / 5.0" }
                improvedVersion = eval.improvedAlternative
                trainerObservation = eval.trainerObservation
                vocabularyUsed = eval.vocabularyUsed
                voiceErrorMessage = null

                // Add Priya's reply to conversation history
                conversationTurns.add(ConversationMessage("assistant", eval.rolePlayReply))

                val detectedLabel = resolveLanguageDisplay(eval.detectedLanguage, eval.rolePlayReply)
                detectedLanguageName = detectedLabel

                if (eval.isSessionConcluding) {
                    isSessionConcluding = true
                    isSessionActive = false
                }

                // Speak the live AI response out loud in the detected regional language or English
                speakAiResponse(eval.rolePlayReply, eval.detectedLanguage)
            } else {
                // Not connected or AI failure - completely omit any dummy data
                isAiConnected = false
                isSessionActive = false
                aiReply = null
                aiFluencyScore = null
                aiAccuracyScore = null
                improvedVersion = null
                trainerObservation = null
                vocabularyUsed = emptyList()
                voiceErrorMessage = eval.errorMessage ?: "AI Coach not connected. Live AI required."
            }
        }
    }

    // 2. Native Speech Recognizer with seamless continuous listening re-arm
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
                spokenVoiceText = liveSpeechDraft
                voiceErrorMessage = null
            },
            onError = { errMsg, isTimeoutOrNoMatch ->
                isListening = false
                if (isSessionActive && !isAiSpeaking && !isEvaluating && isTimeoutOrNoMatch) {
                    // Learner paused to think — seamlessly keep listening without crashing the session
                    mainHandler.postDelayed({
                        if (isSessionActive && !isAiSpeaking && !isEvaluating) {
                            triggerVoiceCaptureAction?.invoke()
                        }
                    }, 400)
                } else if (!isTimeoutOrNoMatch) {
                    voiceErrorMessage = errMsg
                    isSessionActive = false
                }
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
        } else {
            isSessionActive = false
            voiceErrorMessage = "Microphone permission required for Live Voice Coach."
        }
    }

    fun startVoiceCapture() {
        if (!isSessionActive) return
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

    // Connect trigger
    triggerVoiceCaptureAction = {
        startVoiceCapture()
    }

    fun startLiveSession() {
        if (isAiConnected == false) {
            verifyAiConnection()
            return
        }
        isSessionActive = true
        isSessionConcluding = false
        voiceErrorMessage = null

        if (conversationTurns.isEmpty()) {
            conversationTurns.add(ConversationMessage("assistant", initialPriyaGreeting))
            aiReply = initialPriyaGreeting
            speakAiResponse(initialPriyaGreeting, "en")
        } else {
            triggerVoiceCaptureAction?.invoke()
        }
    }

    fun endLiveSession() {
        isSessionActive = false
        isListening = false
        speechRecognizer?.stopListening()
        textToSpeech?.stop()
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
            // Mode B: Interactive Voice-First AI Coach (Live AI Only, No Fallbacks)
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
                    .border(
                        1.5.dp,
                        if (isAiConnected == false) Color(0xFFEF4444).copy(alpha = 0.6f) else ElevoraColors.GoldPrimary.copy(alpha = 0.5f),
                        RoundedCornerShape(22.dp)
                    )
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Live AI Connection Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                when {
                                    isCheckingConnection -> Color(0xFF1E293B)
                                    isAiConnected == true -> Color(0xFF064E3B)
                                    else -> Color(0xFF450A0A)
                                }
                            )
                            .border(
                                1.dp,
                                when {
                                    isCheckingConnection -> Color(0xFF475569)
                                    isAiConnected == true -> Color(0xFF10B981)
                                    else -> Color(0xFFEF4444)
                                },
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isCheckingConnection -> Color(0xFFFBBF24)
                                        isAiConnected == true -> Color(0xFF10B981)
                                        else -> Color(0xFFEF4444)
                                    }
                                )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when {
                                isCheckingConnection -> "Connecting to Live AI..."
                                isAiConnected == true -> "LIVE AI COACH CONNECTED"
                                else -> "NOT CONNECTED"
                            },
                            color = when {
                                isCheckingConnection -> Color(0xFFFDE68A)
                                isAiConnected == true -> Color(0xFFA7F3D0)
                                else -> Color(0xFFFECACA)
                            },
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.6.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Live Voice Speaking Assessment",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = if (isAiConnected == false) {
                            "Live Voice Coach requires an active AI connection. No dummy fallback data is used."
                        } else {
                            "Hands-free voice mode. Speak in Telugu (తెలుగు), English, or Hindi — AI automatically understands and responds out loud."
                        },
                        color = if (isAiConnected == false) Color(0xFFFCA5A5) else ElevoraColors.TextSecondary,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
                    )

                    // Dedicated Lesson 1 Persona Card: Priya Sharma
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF0B132B))
                            .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(ElevoraColors.GoldPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "PS",
                                    color = ElevoraColors.DeepNavy,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Priya Sharma",
                                        color = ElevoraColors.TextPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "SENIOR DIRECTOR",
                                            color = ElevoraColors.GoldPrimary,
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.Black
                                        )
                                    }
                                }
                                Text(
                                    text = "Operations Lead • Meeting you in the corporate office",
                                    color = ElevoraColors.TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Dynamic Language Auto-Detection Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(ElevoraColors.GoldPrimary.copy(alpha = 0.12f))
                            .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (detectedLanguageName == "Auto-Detecting") "🌐 Auto-Detect: Telugu (తెలుగు) / English / Hindi" else "🌐 Active Language: $detectedLanguageName",
                            color = ElevoraColors.GoldPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (isAiConnected == false) {
                        // Not connected banner with reconnect action
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF2B1117))
                                .border(1.dp, Color(0xFFDC2626).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "⚠️ Live AI Coach Offline",
                                    color = Color(0xFFFCA5A5),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = voiceErrorMessage ?: "Cannot reach live AI server. Please verify internet connectivity.",
                                    color = ElevoraColors.TextSecondary,
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Button(
                                    onClick = {
                                        voiceErrorMessage = null
                                        verifyAiConnection()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ElevoraColors.GoldPrimary,
                                        contentColor = ElevoraColors.DeepNavy
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                                ) {
                                    if (isCheckingConnection) {
                                        CircularProgressIndicator(
                                            color = ElevoraColors.DeepNavy,
                                            modifier = Modifier.size(16.dp),
                                            strokeWidth = 2.dp
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.Refresh,
                                            contentDescription = "Retry",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                    Text(
                                        text = "Retry Connection",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    if (!isSessionActive) {
                        // Start Live Continuous Session CTA
                        Button(
                            onClick = { startLiveSession() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isAiConnected == false) Color(0xFF475569) else ElevoraColors.GoldPrimary,
                                contentColor = ElevoraColors.DeepNavy
                            ),
                            shape = RoundedCornerShape(16.dp),
                            enabled = isAiConnected != false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Start Live Session",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Start Live Voice Conversation",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Hands-free continuous dialogue. Speak freely in Telugu, English, or Hindi — AI automatically detects your language, responds out loud, and stays open until you conclude.",
                            color = ElevoraColors.TextSecondary,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    } else {
                        // Live Continuous Session is Active
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .scale(if (isListening) pulseScale else 1f)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isAiSpeaking -> ElevoraColors.GoldPrimary
                                        isEvaluating -> Color(0xFF3B82F6)
                                        isListening -> Color(0xFFDC2626)
                                        else -> ElevoraColors.GoldPrimary
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = when {
                                    isAiSpeaking -> Icons.AutoMirrored.Filled.VolumeUp
                                    isListening -> Icons.Default.Mic
                                    else -> Icons.Default.Refresh
                                },
                                contentDescription = "Status Indicator",
                                tint = if (isListening) Color.White else ElevoraColors.DeepNavy,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = when {
                                isAiSpeaking -> "🗣️ Coach is speaking out loud..."
                                isEvaluating -> "⚡ Assessing speech with live AI..."
                                isListening -> "🎙️ Listening to you... (Speak freely in Telugu or English)"
                                else -> "Connecting..."
                            },
                            color = when {
                                isAiSpeaking -> ElevoraColors.GoldLight
                                isListening -> Color(0xFFFCA5A5)
                                else -> ElevoraColors.TextPrimary
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Red End Call / Conclude Session Button
                        Button(
                            onClick = { endLiveSession() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFDC2626),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Stop,
                                contentDescription = "Conclude Session",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Conclude Session / సంభాషణ ముగించు",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Multi-turn Live Dialogue History
                    if (conversationTurns.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            conversationTurns.takeLast(4).forEach { turn ->
                                DialogueBubble(
                                    speaker = if (turn.speaker == "user") "You (Learner):" else "Priya Sharma (Operations):",
                                    text = turn.text,
                                    isUser = turn.speaker == "user"
                                )
                            }
                        }
                    }

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

            // Real AI Verbal Reply & Assessment Breakdown (Strictly live, never dummy)
            if (aiReply != null && isAiConnected == true) {
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
                                    text = "Coach Spoken Response ($detectedLanguageName)",
                                    color = ElevoraColors.TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                IconButton(
                                    onClick = {
                                        aiReply?.let { speakAiResponse(it) }
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

                            if (aiFluencyScore != null) {
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
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "\"$aiReply\"",
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )

                        if (!improvedVersion.isNullOrBlank()) {
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

                        if (!trainerObservation.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "TRAINER OBSERVATION:",
                                color = ElevoraColors.GoldLight,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.8.sp
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = trainerObservation!!,
                                color = ElevoraColors.TextSecondary,
                                fontSize = 12.sp,
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
