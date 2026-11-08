package com.elevora.app.features.learn.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.learn.model.Day1Curriculum

enum class Day1StepType {
    LESSON_VIDEO,
    WORDS_FOR_WORK,
    LANGUAGE_FOCUS_SAY_IT,
    CONTROLLED_PRACTICE,
    ROLE_PLAY,
    QUIZ,
    REFLECTION
}

data class Day1StepItem(
    val type: Day1StepType,
    val sectionNumber: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val durationText: String,
    val isCurrent: Boolean = false
)

val day1FullStepsList = listOf(
    Day1StepItem(
        Day1StepType.LESSON_VIDEO,
        "01",
        "Lesson Video & Objectives",
        "Objectives: Confident introductions, realistic scenario, 2–5 min speaking",
        Icons.Filled.PlayArrow,
        "5 min",
        isCurrent = true
    ),
    Day1StepItem(
        Day1StepType.WORDS_FOR_WORK,
        "02",
        "Words for Work",
        "Role, department, experience, responsibility, position, team, background, objective",
        Icons.Outlined.Book,
        "10 min"
    ),
    Day1StepItem(
        Day1StepType.LANGUAGE_FOCUS_SAY_IT,
        "03",
        "Language Focus & Say It Phrases",
        "Short sentences, correct tense, asking follow-ups, 6 managerial phrases",
        Icons.Outlined.ChatBubbleOutline,
        "10 min"
    ),
    Day1StepItem(
        Day1StepType.CONTROLLED_PRACTICE,
        "04",
        "Controlled Practice",
        "Form sentences with 5 words, change 3 statements to questions, give workplace example",
        Icons.Outlined.CheckCircleOutline,
        "10 min"
    ),
    Day1StepItem(
        Day1StepType.ROLE_PLAY,
        "05",
        "Manager & Colleague Role-Play",
        "Practice Area: Without AI drill vs With AI Google GenAI live partner",
        Icons.Outlined.RecordVoiceOver,
        "15 min"
    ),
    Day1StepItem(
        Day1StepType.QUIZ,
        "06",
        "Check Your Understanding",
        "5-question comprehension quiz on target vocabulary & expressions",
        Icons.Outlined.Quiz,
        "5 min"
    ),
    Day1StepItem(
        Day1StepType.REFLECTION,
        "07",
        "Lesson Reflection & Homework",
        "Personal goal, mistake to avoid, confidence audit (1–5), 2-min homework",
        Icons.Outlined.EditNote,
        "5 min"
    )
)

@Composable
fun Day1HubScreen(
    onBackClick: () -> Unit = {},
    onStepClick: (Day1StepType) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val curriculum = Day1Curriculum()

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
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
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
            Column {
                Text(
                    text = "Module 1 • Week 1 • Class 1",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Professional Introductions",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Header card with trainer visual banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trainer_priya_banner),
                        contentDescription = "Trainer Priya",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                androidx.compose.ui.graphics.Brush.verticalGradient(
                                    listOf(
                                        androidx.compose.ui.graphics.Color.Transparent,
                                        ElevoraColors.DeepNavy.copy(alpha = 0.95f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "LESSON 1 CURRICULUM",
                            color = ElevoraColors.GoldPrimary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Professional Introductions",
                            color = ElevoraColors.TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Complete the 7-step managerial speaking routine",
                            color = ElevoraColors.TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            items(day1FullStepsList) { step ->
                val isHighlighted = step.isCurrent

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isHighlighted) ElevoraColors.SlateCard else ElevoraColors.MidnightSurface)
                        .border(
                            width = 1.2.dp,
                            color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            shape = RoundedCornerShape(16.dp)
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
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isHighlighted) ElevoraColors.GoldSubtle else ElevoraColors.SlateCardGlass)
                                .border(1.dp, if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = step.icon,
                                contentDescription = null,
                                tint = ElevoraColors.GoldPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Step ${step.sectionNumber}",
                                    color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "(${step.durationText})",
                                    color = ElevoraColors.TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = step.title,
                                color = ElevoraColors.TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = step.subtitle,
                                color = ElevoraColors.TextSecondary,
                                fontSize = 11.sp,
                                maxLines = 1
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}
