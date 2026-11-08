package com.elevora.app.features.learn.screens

import androidx.compose.animation.*
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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard

data class ModuleWeekTopic(
    val weekNumber: Int,
    val title: String,
    val isCurrent: Boolean = false,
    val isLocked: Boolean = false,
    val lessons: List<String>
)

val allModule1Weeks = listOf(
    ModuleWeekTopic(
        1, "Professional Introductions & Workplace Routine",
        isCurrent = true,
        isLocked = false,
        lessons = listOf("Lesson 1: Professional Introductions", "Lesson 2: Job Roles & Responsibilities", "Lesson 3: Present Simple for Work")
    ),
    ModuleWeekTopic(
        2, "Past & Future Workplace Communication",
        isCurrent = false,
        isLocked = true,
        lessons = listOf("Lesson 4: Past Experiences", "Lesson 5: Future Plans & Commitments", "Lesson 6: Questions & Clarification")
    ),
    ModuleWeekTopic(
        3, "Workplace Foundations & Describing Situations",
        isCurrent = false,
        isLocked = true,
        lessons = listOf("Lesson 7: Workplace Vocabulary", "Lesson 8: Describing People & Situations", "Lesson 9: Time, Schedules & Routines")
    ),
    ModuleWeekTopic(
        4, "Grammar Accuracy & Conversational Fluency",
        isCurrent = false,
        isLocked = true,
        lessons = listOf("Lesson 10: Common Grammar Errors", "Lesson 11: Confidence-Building Conversations", "Lesson 12: Module 1 Review")
    )
)

@Composable
fun ModuleOverviewScreen(
    onBackClick: () -> Unit = {},
    onWeekClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
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
                    text = "Module 1",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Building Confidence & Core Workplace English",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Realistic High-End Visual Boardroom Banner
            item {
                ElevoraGlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 20.dp
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.module1_banner),
                                contentDescription = "Executive Boardroom",
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
                                                ElevoraColors.DeepNavy.copy(alpha = 0.9f)
                                            )
                                        )
                                    )
                            )
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                BadgePill(icon = "📅", text = "4 Weeks")
                                BadgePill(icon = "📖", text = "12 Classes")
                                BadgePill(icon = "🏆", text = "Beginner")
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Build essential English skills for everyday workplace success: professional introductions, job roles, daily schedules, and clear questions.",
                            color = ElevoraColors.TextSecondary,
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            item {
                Text(
                    text = "WEEKS IN THIS MODULE",
                    color = ElevoraColors.TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(allModule1Weeks) { week ->
                val isSelected = week.isCurrent
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) ElevoraColors.SlateCard else ElevoraColors.MidnightSurface)
                        .border(
                            width = 1.2.dp,
                            color = if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable(enabled = !week.isLocked) { onWeekClick(week.weekNumber) }
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) ElevoraColors.GoldSubtle else ElevoraColors.SlateCardGlass)
                                    .border(1.dp, if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (week.isLocked) {
                                    Icon(
                                        imageVector = Icons.Outlined.Lock,
                                        contentDescription = "Locked",
                                        tint = ElevoraColors.TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                } else {
                                    Text(text = "W${week.weekNumber}", color = ElevoraColors.GoldPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Week ${week.weekNumber}",
                                    color = if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = week.title,
                                    color = if (week.isLocked) ElevoraColors.TextMuted else ElevoraColors.TextPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            if (!week.isLocked) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = ElevoraColors.GoldPrimary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        // Lesson topics list for that week
                        Spacer(modifier = Modifier.height(10.dp))
                        week.lessons.forEach { lessonTitle ->
                            Row(
                                modifier = Modifier.padding(start = 52.dp, top = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(4.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = lessonTitle,
                                    color = ElevoraColors.TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun BadgePill(icon: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(ElevoraColors.SlateCardGlass)
            .border(1.dp, ElevoraColors.CardStrokeSubtle, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = icon, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = ElevoraColors.TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
