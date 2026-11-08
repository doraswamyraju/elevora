package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.learn.model.Day1FullContent

enum class Day1StepType {
    LESSON_VIDEO,
    LEARN_EXPRESSIONS,
    PRACTICE_SPEAK,
    APPLY_ACTIVITY,
    QUIZ
}

data class Day1Step(
    val type: Day1StepType,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val durationText: String,
    val isCompleted: Boolean = false,
    val isCurrent: Boolean = false
)

val day1StepsList = listOf(
    Day1Step(
        type = Day1StepType.LESSON_VIDEO,
        title = "Lesson",
        subtitle = "Course Overview & Learning Goals",
        icon = Icons.Filled.PlayArrow,
        durationText = "(5 min)",
        isCurrent = true
    ),
    Day1Step(
        type = Day1StepType.LEARN_EXPRESSIONS,
        title = "Learn",
        subtitle = "Key Expressions – Getting Started",
        icon = Icons.Outlined.Book,
        durationText = ""
    ),
    Day1Step(
        type = Day1StepType.PRACTICE_SPEAK,
        title = "Practice",
        subtitle = "Speak & Repeat",
        icon = Icons.Outlined.Mic,
        durationText = ""
    ),
    Day1Step(
        type = Day1StepType.APPLY_ACTIVITY,
        title = "Apply",
        subtitle = "Quick Activity",
        icon = Icons.Outlined.CheckCircleOutline,
        durationText = "(3 min)"
    ),
    Day1Step(
        type = Day1StepType.QUIZ,
        title = "Quiz",
        subtitle = "Check Your Understanding",
        icon = Icons.Outlined.Quiz,
        durationText = "(5 min)"
    )
)

/**
 * Screen matching Screenshot 4: Module 1 – Week 1 – Day 1 with 5 Steps
 */
@Composable
fun Day1HubScreen(
    onBackClick: () -> Unit = {},
    onStepClick: (Day1StepType) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val content = Day1FullContent()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.SlateCard)
                    .border(1.dp, ElevoraColors.CardStroke, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ElevoraColors.TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Module 1 – Week 1 – Day 1",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Day Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(ElevoraColors.GoldPrimary)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Day 1",
                        color = ElevoraColors.DeepNavy,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = content.title,
                    color = ElevoraColors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = content.subtitle,
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            items(day1StepsList) { step ->
                val isHighlighted = step.isCurrent

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isHighlighted) ElevoraColors.SlateCard else ElevoraColors.DarkVoid)
                        .border(
                            width = 1.dp,
                            color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable { onStepClick(step.type) }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (isHighlighted) ElevoraColors.GoldSubtle else ElevoraColors.SlateCardGlass)
                                .border(
                                    1.dp,
                                    if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = step.icon,
                                contentDescription = null,
                                tint = ElevoraColors.GoldPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = step.title,
                                    color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.TextSecondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (step.durationText.isNotEmpty()) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = step.durationText,
                                        color = ElevoraColors.TextMuted,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = step.subtitle,
                                color = ElevoraColors.TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

