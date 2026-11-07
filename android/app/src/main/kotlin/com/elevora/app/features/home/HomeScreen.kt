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
import com.elevora.app.features.home.components.ContinueLearningCard
import com.elevora.app.features.home.components.HeroProgressCard
import com.elevora.app.features.home.components.MetricsRow
import com.elevora.app.features.home.components.QuickPracticeDock

/**
 * Elevora Home Dashboard Screen
 */
@Composable
fun HomeScreen(
    onContinueLearningClick: () -> Unit = {},
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
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        HeroProgressCard(
            programmeName = "Managerial English",
            durationInfo = "6 Months • 72 Lessons",
            progress = 0.18f
        )

        Spacer(modifier = Modifier.height(16.dp))

        MetricsRow(
            weekText = "3 of 24",
            lessonsText = "14 / 72",
            streakText = "5 Days"
        )

        Spacer(modifier = Modifier.height(20.dp))

        ContinueLearningCard(
            lessonTitle = "Lesson 7",
            subtitle = "Present Simple for Work",
            meta = "Module 1 • 12 min",
            onPlayClick = onContinueLearningClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        QuickPracticeDock(
            onSpeakingClick = onSpeakingClick,
            onQuizClick = onQuizClick,
            onVocabClick = onVocabClick,
            onProgressClick = onProgressClick
        )

        Spacer(modifier = Modifier.height(80.dp)) // padding for bottom bar
    }
}

