package com.elevora.app.core.network

import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.SSLException

data class AiSpeakingEvaluation(
    val rolePlayReply: String? = null,
    val fluencyScore: Float? = null,
    val accuracyScore: Float? = null,
    val improvedAlternative: String? = null,
    val trainerObservation: String? = null,
    val vocabularyUsed: List<String> = emptyList(),
    val grammarTips: List<String> = emptyList(),
    val totalTokens: Int = 0,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

/**
 * Native HTTP client for Elevora Live AI Backend API
 * Connects exclusively to live AI service (Google GenAI).
 * No fallback model, no dummy or simulated responses.
 */
object ElevoraApiClient {
    private const val PRODUCTION_BASE_URL = "https://elevora.sriddha.com"
    private const val EMULATOR_LOCAL_URL = "http://10.0.2.2:5090"

    private val isEmulator: Boolean by lazy {
        Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
    }

    // On physical phones (like TECNO KM8), strictly use production HTTPS domain.
    // 10.0.2.2 is only included when running on an Android SDK Emulator.
    private val candidateUrls: List<String>
        get() = if (isEmulator) {
            listOf(PRODUCTION_BASE_URL, EMULATOR_LOCAL_URL)
        } else {
            listOf(PRODUCTION_BASE_URL)
        }

    /**
     * Checks if the Live AI Coach service is online and accessible.
     */
    suspend fun checkAiConnection(): Boolean = withContext(Dispatchers.IO) {
        for (baseUrl in candidateUrls) {
            try {
                val url = URL("$baseUrl/api/course/day1")
                val connection = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "GET"
                    setRequestProperty("Accept", "application/json")
                    setRequestProperty("User-Agent", "Elevora/1.0 (Android; ${Build.MANUFACTURER} ${Build.MODEL})")
                    connectTimeout = 10000
                    readTimeout = 10000
                }
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    return@withContext true
                }
            } catch (_: Exception) {
                // Continue to next candidate URL
            }
        }
        false
    }

    suspend fun sendSpeakingPractice(
        userSpokenText: String,
        targetScenario: String = "Module 1, Lesson 1: Professional Self-Introduction",
        apiKey: String? = null
    ): AiSpeakingEvaluation = withContext(Dispatchers.IO) {
        if (userSpokenText.isBlank()) {
            return@withContext AiSpeakingEvaluation(
                isSuccess = false,
                errorMessage = "No speech detected. Please speak into the microphone."
            )
        }

        var lastError = "Could not reach Live AI Coach service."

        for (baseUrl in candidateUrls) {
            val endpoint = "$baseUrl/api/ai/practice-feedback"
            try {
                val url = URL(endpoint)
                val connection = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    setRequestProperty("Accept", "application/json")
                    setRequestProperty("User-Agent", "Elevora/1.0 (Android; ${Build.MANUFACTURER} ${Build.MODEL})")
                    doOutput = true
                    connectTimeout = 15000
                    readTimeout = 30000
                }

                val jsonBody = JSONObject().apply {
                    put("userSpokenText", userSpokenText)
                    put("scenarioContext", targetScenario)
                    if (!apiKey.isNullOrBlank()) {
                        put("apiKey", apiKey)
                    }
                }

                OutputStreamWriter(connection.outputStream).use { writer ->
                    writer.write(jsonBody.toString())
                    writer.flush()
                }

                val responseCode = connection.responseCode
                val stream = if (responseCode in 200..299) connection.inputStream else connection.errorStream
                val responseText = stream?.bufferedReader()?.use { it.readText() }.orEmpty()

                if (responseCode == HttpURLConnection.HTTP_OK && responseText.isNotBlank()) {
                    val rootJson = JSONObject(responseText)
                    val success = rootJson.optBoolean("success", false)
                    val isSimulated = rootJson.optBoolean("isSimulated", false)

                    // Strictly reject any simulated dummy data
                    if (!success || isSimulated) {
                        lastError = rootJson.optString("message", "AI Coach is not connected.")
                        continue
                    }

                    val reply = rootJson.optString("rolePlayReply", "").trim()
                    if (reply.isEmpty()) {
                        lastError = "AI Coach returned an empty response."
                        continue
                    }

                    val feedbackObj = rootJson.optJSONObject("feedback")
                    val fluency = if (feedbackObj != null && feedbackObj.has("fluencyScore")) {
                        feedbackObj.optDouble("fluencyScore", 0.0).toFloat()
                    } else null

                    val accuracy = if (feedbackObj != null && feedbackObj.has("accuracyScore")) {
                        feedbackObj.optDouble("accuracyScore", 0.0).toFloat()
                    } else null

                    val improved = feedbackObj?.optString("improvedAlternative", "")?.takeIf { it.isNotBlank() }
                    val trainerObs = feedbackObj?.optString("trainerObservation", "")?.takeIf { it.isNotBlank() }

                    val vocabList = mutableListOf<String>()
                    val vocabArray = feedbackObj?.optJSONArray("vocabularyUsed")
                    if (vocabArray != null) {
                        for (i in 0 until vocabArray.length()) {
                            vocabList.add(vocabArray.getString(i))
                        }
                    }

                    val tipsList = mutableListOf<String>()
                    if (!trainerObs.isNullOrBlank()) {
                        tipsList.add(trainerObs)
                    }

                    val telemetry = rootJson.optJSONObject("telemetry")
                    val tokens = telemetry?.optInt("totalTokens", 0) ?: 0

                    return@withContext AiSpeakingEvaluation(
                        rolePlayReply = reply,
                        fluencyScore = fluency,
                        accuracyScore = accuracy,
                        improvedAlternative = improved,
                        trainerObservation = trainerObs,
                        vocabularyUsed = vocabList,
                        grammarTips = tipsList,
                        totalTokens = tokens,
                        isSuccess = true,
                        errorMessage = null
                    )
                } else if (responseText.isNotBlank()) {
                    try {
                        val errObj = JSONObject(responseText)
                        lastError = errObj.optString("message", "AI Coach error (HTTP $responseCode)")
                    } catch (_: Exception) {
                        lastError = "AI Coach service unavailable (HTTP $responseCode)"
                    }
                }
            } catch (e: java.net.UnknownHostException) {
                lastError = "No internet connection or DNS error. Please verify your mobile data or Wi-Fi."
            } catch (e: java.net.SocketTimeoutException) {
                lastError = "Connection timed out. Please check signal strength and tap Retry."
            } catch (e: SSLException) {
                lastError = "Secure SSL error. Please verify device date and time."
            } catch (e: java.io.IOException) {
                lastError = e.localizedMessage ?: "Network connection error"
            } catch (e: Exception) {
                lastError = e.localizedMessage ?: "Failed to connect to Live AI service."
            }
        }

        // Return error result — strictly live, NO fallback model or dummy data
        return@withContext AiSpeakingEvaluation(
            rolePlayReply = null,
            fluencyScore = null,
            accuracyScore = null,
            improvedAlternative = null,
            trainerObservation = null,
            vocabularyUsed = emptyList(),
            grammarTips = emptyList(),
            totalTokens = 0,
            isSuccess = false,
            errorMessage = lastError
        )
    }
}
