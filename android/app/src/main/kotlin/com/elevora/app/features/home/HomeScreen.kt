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
import com.elevora.app.features.home.components.MetricsRow
import com.elevora.app.features.home.components.QuickPracticeDock

/**
 * Elevora Luxury Home Dashboard Screen:
 * Clean, uncluttered layout with authentic branding, ambient lighting,
 * and high-converting pedagogical milestones.
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
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        // 1. Active Course Hero Card with Graphic Backdrop & Ambient Glow
        HeroProgressCard(
            moduleTag = "MODULE 1 • FOUNDATIONS",
            moduleTitle = "Managerial English Foundations",
            weekInfo = "Week 1 of 4",
            dayProgressPercent = 0.85f,
            activeDayTitle = "Day 1: Professional Introductions",
            onContinueClick = onContinueLearningClick
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 2. 2x2 Grid of Luxury Quick Practice Cards
        QuickPracticeDock(
            onSpeakingClick = onSpeakingClick,
            onQuizClick = onQuizClick,
            onVocabClick = onVocabClick,
            onProgressClick = onProgressClick
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 3. Daily Learning Journey: Connected Node Stepper & Active Milestone Showcase
        Day1SequenceTrackerCard(
            currentStepIndex = 4, // Step 5: Role-Play
            onStepClick = onStepClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Weekly Metric Summary Row
        MetricsRow(
            weekText = "1 of 24",
            lessonsText = "4 / 7 Steps",
            streakText = "$streakDays Days"
        )

        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom navigation
    }
}
