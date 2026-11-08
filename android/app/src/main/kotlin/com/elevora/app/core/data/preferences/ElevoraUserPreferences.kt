package com.elevora.app.core.data.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Robust local persistence manager for learner state,
 * daily streak, lesson completion, and quiz scores.
 */
class ElevoraUserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "elevora_learner_prefs"
        private const val KEY_USER_NAME = "key_user_name"
        private const val KEY_USER_EMAIL = "key_user_email"
        private const val KEY_STREAK_DAYS = "key_streak_days"
        private const val KEY_LAST_STUDIED_TIMESTAMP = "key_last_studied_timestamp"
        private const val KEY_CURRENT_MODULE = "key_current_module"
        private const val KEY_CURRENT_WEEK = "key_current_week"
        private const val KEY_CURRENT_DAY = "key_current_day"
        private const val KEY_CURRENT_STEP = "key_current_step"
        private const val KEY_COMPLETED_STEPS_CSV = "key_completed_steps_csv"
        private const val KEY_DAY1_QUIZ_SCORE = "key_day1_quiz_score"
        private const val KEY_AI_PRACTICE_COUNT = "key_ai_practice_count"
    }

    var userName: String
        get() = prefs.getString(KEY_USER_NAME, "Raju") ?: "Raju"
        set(value) = prefs.edit().putString(KEY_USER_NAME, value).apply()

    var userEmail: String
        get() = prefs.getString(KEY_USER_EMAIL, "user@elevora.com") ?: "user@elevora.com"
        set(value) = prefs.edit().putString(KEY_USER_EMAIL, value).apply()

    var streakDays: Int
        get() = prefs.getInt(KEY_STREAK_DAYS, 4)
        set(value) = prefs.edit().putInt(KEY_STREAK_DAYS, value).apply()

    var currentModule: Int
        get() = prefs.getInt(KEY_CURRENT_MODULE, 1)
        set(value) = prefs.edit().putInt(KEY_CURRENT_MODULE, value).apply()

    var currentWeek: Int
        get() = prefs.getInt(KEY_CURRENT_WEEK, 1)
        set(value) = prefs.edit().putInt(KEY_CURRENT_WEEK, value).apply()

    var currentDay: Int
        get() = prefs.getInt(KEY_CURRENT_DAY, 1)
        set(value) = prefs.edit().putInt(KEY_CURRENT_DAY, value).apply()

    var currentStep: Int
        get() = prefs.getInt(KEY_CURRENT_STEP, 4) // Step 4 (Role-play)
        set(value) = prefs.edit().putInt(KEY_CURRENT_STEP, value).apply()

    var day1QuizScore: Int
        get() = prefs.getInt(KEY_DAY1_QUIZ_SCORE, 100)
        set(value) = prefs.edit().putInt(KEY_DAY1_QUIZ_SCORE, value).apply()

    var aiPracticeCount: Int
        get() = prefs.getInt(KEY_AI_PRACTICE_COUNT, 3)
        set(value) = prefs.edit().putInt(KEY_AI_PRACTICE_COUNT, value).apply()

    fun markStepCompleted(stepIndex: Int) {
        val currentSet = getCompletedSteps().toMutableSet()
        currentSet.add(stepIndex)
        prefs.edit().putString(KEY_COMPLETED_STEPS_CSV, currentSet.joinToString(",")).apply()
    }

    fun getCompletedSteps(): Set<Int> {
        val csv = prefs.getString(KEY_COMPLETED_STEPS_CSV, "0,1,2,3") ?: "0,1,2,3"
        return csv.split(",").mapNotNull { it.trim().toIntOrNull() }.toSet()
    }

    fun recordDailyActivity() {
        val now = System.currentTimeMillis()
        val lastStudied = prefs.getLong(KEY_LAST_STUDIED_TIMESTAMP, 0L)
        val oneDayMillis = 24 * 60 * 60 * 1000L

        if (lastStudied == 0L) {
            streakDays = 1
        } else if (now - lastStudied in oneDayMillis..(2 * oneDayMillis)) {
            streakDays += 1
        }
        prefs.edit().putLong(KEY_LAST_STUDIED_TIMESTAMP, now).apply()
    }
}

