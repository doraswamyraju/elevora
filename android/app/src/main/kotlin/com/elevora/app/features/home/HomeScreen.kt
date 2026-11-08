package com.elevora.app.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.home.components.Day1SequenceTrackerCard
import com.elevora.app.features.home.components.HeroProgressCard
import com.elevora.app.features.home.components.HomeGreetingHeader
import com.elevora.app.features.home.components.MetricsRow
import com.elevora.app.features.home.components.QuickPracticeDock

/**
 * Elevora Complete Home Dashboard Screen
 * Built with modular, highly responsive components matching the official design.
 */
@Composable
fun HomeScreen(
    userName: String = "Raju",
    streakDays: Int = 4,
    onContinueLearningClick: () -> Unit = {},
    onStepClick: (Int) -> Unit = {},
    onSpeakingClick: () -> Unit = {},
    onQuizClick: () -> Unit = {},
    onVocabClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // 1. Personalized User Greeting with Streak
        HomeGreetingHeader(
            userName = userName,
            streakDays = streakDays
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 2. Active Course Hero Card (Module 1 Foundations)
        HeroProgressCard(
            moduleTag = "MODULE 1 • FOUNDATIONS",
            moduleTitle = "Managerial English Foundations",
            weekInfo = "Week 1 of 4",
            dayProgressPercent = 0.85f,
            activeDayTitle = "Day 1: Professional Introductions",
            onContinueClick = onContinueLearningClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Quick Action Dock (AI Tutor, Practice, Quiz, Progress)
        QuickPracticeDock(
            onSpeakingClick = onSpeakingClick,
            onQuizClick = onQuizClick,
            onVocabClick = onVocabClick,
            onProgressClick = onProgressClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Daily Learning Path: 7-Step Sequence Tracker
        Day1SequenceTrackerCard(
            currentStepIndex = 4, // Step 5: Role-Play is next up
            onStepClick = onStepClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Weekly Metric Counters
        MetricsRow(
            weekText = "1 of 24",
            lessonsText = "4 / 7 Steps",
            streakText = "$streakDays Days"
        )

        Spacer(modifier = Modifier.height(90.dp)) // padding for bottom navigation
    }
}
