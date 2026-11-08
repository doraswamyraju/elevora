package com.elevora.app.features.learn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.learn.model.Day1FullContent

enum class LessonTab {
    OVERVIEW,
    TRANSCRIPT,
    NOTES
}

/**
 * Screen matching Screenshot 5: Lesson Video Player + Overview/Transcript/Notes tabs + Next Lesson Button
 */
@Composable
fun Day1LessonVideoScreen(
    onBackClick: () -> Unit = {},
    onNextLesson: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val content = Day1FullContent()
    var selectedTab by remember { mutableStateOf(LessonTab.OVERVIEW) }
    var isPlaying by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
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
                text = "Lesson",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Mock Video Player Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ElevoraColors.MidnightSurface)
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.elevora_logo_square),
                    contentDescription = "Trainer Video",
                    modifier = Modifier
                        .size(68.dp)
                        .clip(CircleShape)
                        .border(2.dp, ElevoraColors.GoldPrimary, CircleShape)
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Play button overlay
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(ElevoraColors.GoldPrimary)
                        .clickable { isPlaying = !isPlaying },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play Video",
                        tint = ElevoraColors.DeepNavy,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Duration Pill
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(ElevoraColors.DeepNavy.copy(alpha = 0.8f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "0:00 / ${content.videoDuration}",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs: Overview | Transcript | Notes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf(
                LessonTab.OVERVIEW to "Overview",
                LessonTab.TRANSCRIPT to "Transcript",
                LessonTab.NOTES to "Notes"
            ).forEach { (tab, label) ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) ElevoraColors.SlateCard else ElevoraColors.DarkVoid)
                        .border(
                            1.dp,
                            if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { selectedTab = tab }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        color = if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tab Content
        when (selectedTab) {
            LessonTab.OVERVIEW -> {
                Text(
                    text = content.videoTitle,
                    color = ElevoraColors.TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "In this lesson, we will:",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                content.overviewPoints.forEach { point ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "• ",
                            color = ElevoraColors.GoldPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = point,
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
            LessonTab.TRANSCRIPT -> {
                Text(
                    text = "Full Lesson Transcript",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Welcome to the Managerial English Development Programme. Over the next 24 weeks and 6 modules, our goal is to help you speak confidently, accurately, and naturally in workplace scenarios. Today in Day 1, we focus on setting personal goals and practicing core introductory phrases. Remember: do not aim for perfect English, aim for clear, professional English.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
            LessonTab.NOTES -> {
                Text(
                    text = "Trainer Tips & Takeaways",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "1. Keep a personal notebook of 10-15 high-value words per week.\n2. Record yourself speaking for 2-5 minutes at least three times a week.\n3. Always complete the lesson self-reflection.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Next Lesson Button
        ElevoraPrimaryButton(
            text = "Next: Key Expressions",
            onClick = onNextLesson
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}
