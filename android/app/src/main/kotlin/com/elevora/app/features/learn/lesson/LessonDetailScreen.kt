package com.elevora.app.features.learn.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.features.learn.lesson.components.LearningObjectivesList
import com.elevora.app.features.learn.lesson.components.LessonMediaBanner
import com.elevora.app.features.learn.lesson.components.LessonStep
import com.elevora.app.features.learn.lesson.components.LessonStepTabRow

/**
 * Detailed 7-Part Lesson Flow Screen
 */
@Composable
fun LessonDetailScreen(
    lessonTitle: String = "Present Simple for Work",
    metaInfo: String = "Module 1 • Week 1 • Class 3",
    onWordsForWorkClick: () -> Unit = {},
    onLanguageFocusClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedStep by remember { mutableStateOf(LessonStep.LEARN) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        LessonMediaBanner(durationText = "05:24")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = lessonTitle,
            color = ElevoraColors.TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = metaInfo,
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LessonStepTabRow(
            selectedStep = selectedStep,
            onStepSelected = { selectedStep = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LearningObjectivesList(
            objectives = listOf(
                "Use English confidently for present simple at work.",
                "Apply the target language in a realistic managerial situation.",
                "Speak clearly for 2–5 minutes with reduced hesitation."
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevoraGlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .clickable(onClick = onWordsForWorkClick),
            cornerRadius = 14.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.MenuBook,
                    contentDescription = null,
                    tint = ElevoraColors.GoldPrimary,
                    modifier = Modifier.size(22.dp)
                )
                Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                    Text(
                        text = "Words for Work (8)",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "manage, handle, attend, report...",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 12.sp
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = ElevoraColors.TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        ElevoraGlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .clickable(onClick = onLanguageFocusClick),
            cornerRadius = 14.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = ElevoraColors.CyanCategory,
                    modifier = Modifier.size(22.dp)
                )
                Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                    Text(
                        text = "Language Focus",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Use short, clear sentences; choose the correct tense...",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 12.sp
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = ElevoraColors.TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

