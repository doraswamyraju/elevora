package com.elevora.app.core.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import java.io.File
import java.io.IOException

/**
 * Native Android Audio Recording and Playback Manager
 * Provides 100% Phase 1 self-contained audio capture for learner speaking tasks
 */
class ElevoraAudioRecorder(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var currentRecordingFile: File? = null

    /**
     * Start recording learner speech to an isolated app cache file
     */
    fun startRecording(filenamePrefix: String = "practice_speech"): File? {
        stopRecording()
        stopPlayback()

        val outputDir = File(context.cacheDir, "audio_practice").apply {
            if (!exists()) mkdirs()
        }
        val file = File(outputDir, "${filenamePrefix}_${System.currentTimeMillis()}.mp4")
        currentRecordingFile = file

        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
            MediaRecorder()
        }.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(128000)
            setAudioSamplingRate(44100)
            setOutputFile(file.absolutePath)
            try {
                prepare()
                start()
                Log.d("ElevoraAudio", "Recording started: ${file.absolutePath}")
            } catch (e: IOException) {
                Log.e("ElevoraAudio", "Error starting recording: ${e.message}")
                return null
            }
        }
        return file
    }

    /**
     * Stop the current speech recording
     */
    fun stopRecording(): File? {
        return try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            Log.d("ElevoraAudio", "Recording stopped. File: ${currentRecordingFile?.absolutePath}")
            currentRecordingFile
        } catch (e: Exception) {
            Log.e("ElevoraAudio", "Error stopping recording: ${e.message}")
            mediaRecorder = null
            null
        }
    }

    /**
     * Play recorded learner audio back
     */
    fun playRecording(
        file: File? = currentRecordingFile,
        onCompletion: () -> Unit = {}
    ) {
        if (file == null || !file.exists()) return

        stopPlayback()

        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(file.absolutePath)
                prepare()
                setOnCompletionListener {
                    onCompletion()
                    stopPlayback()
                }
                start()
                Log.d("ElevoraAudio", "Playback started: ${file.absolutePath}")
            } catch (e: IOException) {
                Log.e("ElevoraAudio", "Error playing audio: ${e.message}")
            }
        }
    }

    /**
     * Stop any active audio playback
     */
    fun stopPlayback() {
        try {
            mediaPlayer?.apply {
                if (isPlaying) stop()
                release()
            }
            mediaPlayer = null
        } catch (e: Exception) {
            mediaPlayer = null
        }
    }

    /**
     * Release all hardware audio resources
     */
    fun release() {
        stopRecording()
        stopPlayback()
    }
}
