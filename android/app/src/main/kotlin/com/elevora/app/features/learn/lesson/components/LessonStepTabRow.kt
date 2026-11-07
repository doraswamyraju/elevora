package com.elevora.app.features.learn.lesson.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

enum class LessonStep(val label: String) {
    LEARN("Learn"),
    PRACTICE("Practice"),
    SPEAK("Speak"),
    QUIZ("Quiz")
}

@Composable
fun LessonStepTabRow(
    selectedStep: LessonStep,
    onStepSelected: (LessonStep) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LessonStep.values().forEach { step ->
            val isSelected = step == selectedStep
            val shape = RoundedCornerShape(20.dp)
            Box(
                modifier = Modifier
                    .clip(shape)
                    .background(
                        if (isSelected) ElevoraColors.GoldButtonGradient
                        else androidx.compose.ui.graphics.SolidColor(ElevoraColors.SlateCard)
                    )
                    .clickable { onStepSelected(step) }
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step.label,
                    color = if (isSelected) ElevoraColors.DarkVoid else ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            }
        }
    }
}

