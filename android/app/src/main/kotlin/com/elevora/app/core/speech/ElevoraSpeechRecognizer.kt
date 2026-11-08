package com.elevora.app.core.speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import java.util.Locale

/**
 * Native Android Speech-To-Text Recognizer:
 * Converts learner live voice into real-time transcription.
 * 
 * - onPartial: Updates live preview as the user speaks without triggering AI evaluations.
 * - onFinalResult: Invoked ONLY when the learner finishes speaking, stopping the mic and
 *   passing the complete sentence to the AI coach.
 */
class ElevoraSpeechRecognizer(
    private val context: Context,
    private val onFinalResult: (String) -> Unit,
    private val onPartial: (String) -> Unit = {},
    private val onError: (String) -> Unit = {},
    private val onListeningStateChanged: (Boolean) -> Unit = {}
) {
    private var speechRecognizer: SpeechRecognizer? = null
    private var accumulatedSpeech = ""

    init {
        initializeRecognizer()
    }

    private fun initializeRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
                setRecognitionListener(object : RecognitionListener {
                    override fun onReadyForSpeech(params: Bundle?) {
                        Log.d("ElevoraSpeech", "Ready for speech")
                        accumulatedSpeech = ""
                        onListeningStateChanged(true)
                    }

                    override fun onBeginningOfSpeech() {
                        Log.d("ElevoraSpeech", "User began speaking")
                    }

                    override fun onRmsChanged(rmsdB: Float) {}

                    override fun onBufferReceived(buffer: ByteArray?) {}

                    override fun onEndOfSpeech() {
                        Log.d("ElevoraSpeech", "User stopped speaking")
                        onListeningStateChanged(false)
                    }

                    override fun onError(error: Int) {
                        val message = when (error) {
                            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                            SpeechRecognizer.ERROR_CLIENT -> "Client error"
                            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Microphone permission required"
                            SpeechRecognizer.ERROR_NETWORK -> "Network error"
                            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                            SpeechRecognizer.ERROR_NO_MATCH -> "No speech recognized"
                            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Speech recognition busy"
                            SpeechRecognizer.ERROR_SERVER -> "Server error"
                            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech detected"
                            else -> "Recognition error: $error"
                        }
                        Log.w("ElevoraSpeech", message)
                        onListeningStateChanged(false)
                        onError(message)
                    }

                    override fun onResults(results: Bundle?) {
                        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        val recognizedText = matches?.firstOrNull() ?: accumulatedSpeech
                        Log.d("ElevoraSpeech", "Final recognized speech: $recognizedText")
                        onListeningStateChanged(false)
                        if (recognizedText.isNotBlank()) {
                            onFinalResult(recognizedText)
                        }
                    }

                    override fun onPartialResults(partialResults: Bundle?) {
                        val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        val partial = matches?.firstOrNull()
                        if (!partial.isNullOrBlank()) {
                            accumulatedSpeech = partial
                            onPartial(partial) // ONLY updates the live preview text, does NOT trigger AI API
                        }
                    }

                    override fun onEvent(eventType: Int, params: Bundle?) {}
                })
            }
        } else {
            onError("Speech recognition not supported on this device.")
        }
    }

    /**
     * Start listening to learner voice.
     * Generous silence timeout allows natural pauses without interrupting the learner.
     */
    fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toLanguageTag())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            // Allow 3 seconds of pause before considering speech complete
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 3000L)
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 2500L)
        }
        try {
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            Log.e("ElevoraSpeech", "Failed to start listening: ${e.message}")
            onError(e.message ?: "Failed to start speech recognition")
        }
    }

    /**
     * Stop listening manually (e.g. user taps the stop button)
     */
    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
            onListeningStateChanged(false)
        } catch (e: Exception) {
            Log.e("ElevoraSpeech", "Failed to stop listening: ${e.message}")
        }
    }

    /**
     * Release recognizer resources
     */
    fun destroy() {
        try {
            speechRecognizer?.destroy()
            speechRecognizer = null
        } catch (e: Exception) {
            Log.e("ElevoraSpeech", "Failed to destroy: ${e.message}")
        }
    }
}


