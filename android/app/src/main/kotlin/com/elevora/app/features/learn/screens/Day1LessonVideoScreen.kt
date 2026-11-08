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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.learn.model.Day1Curriculum

enum class VideoTab {
    OBJECTIVES,
    WARM_UP,
    TRAINER_NOTES
}

@Composable
fun Day1LessonVideoScreen(
    onBackClick: () -> Unit = {},
    onNextLesson: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val curriculum = Day1Curriculum()
    var selectedTab by remember { mutableStateOf(VideoTab.OBJECTIVES) }
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
                    text = "Step 01 • Lesson Video",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = curriculum.lessonTitle,
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        // Executive Video Player Card with Trainer Visual
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clip(RoundedCornerShape(18.dp))
                .border(1.2.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.trainer_priya_banner),
                contentDescription = "Executive Trainer Video",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ElevoraColors.DeepNavy.copy(alpha = 0.5f))
            )

            // Play Button with Gold Ripple Animation
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.GoldPrimary)
                    .clickable { isPlaying = !isPlaying },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = ElevoraColors.DeepNavy,
                    modifier = Modifier.size(36.dp)
                )
            }

            // Duration Pill
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(ElevoraColors.DeepNavy.copy(alpha = 0.85f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (isPlaying) "Playing: 01:24 / ${curriculum.videoDuration}" else "00:00 / ${curriculum.videoDuration}",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Tabs: Objectives | Warm-up | Trainer Notes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                VideoTab.OBJECTIVES to "Objectives",
                VideoTab.WARM_UP to "5-Min Warm-up",
                VideoTab.TRAINER_NOTES to "Trainer Notes"
            ).forEach { (tab, label) ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) ElevoraColors.SlateCard else ElevoraColors.MidnightSurface)
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
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        when (selectedTab) {
            VideoTab.OBJECTIVES -> {
                Text(
                    text = "Today I will be able to…",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                curriculum.objectives.forEach { obj ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(text = "• ", color = ElevoraColors.GoldPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(text = obj, color = ElevoraColors.TextPrimary, fontSize = 13.sp, lineHeight = 19.sp)
                    }
                }
            }
            VideoTab.WARM_UP -> {
                Text(
                    text = "5-Min Warm-up: Answer using complete sentences",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                listOf(
                    "1. What is your current role and department?",
                    "2. How many people are on your direct team?",
                    "3. What is one major objective you are working on this week?"
                ).forEach { q ->
                    Text(text = q, color = ElevoraColors.TextPrimary, fontSize = 13.sp, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
            VideoTab.TRAINER_NOTES -> {
                Text(
                    text = "Trainer Guidance (From Trainer Guide)",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Target learner: A working manager with basic-to-intermediate English who needs greater accuracy, confidence, and fluency.\n\nCore approach: Speaking-first, practical, and connected to your real workplace. Do not aim for perfect grammar; aim for clear, assertive executive communication.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        ElevoraPrimaryButton(
            text = "Next: Words for Work (8 Words)",
            onClick = onNextLesson
        )

        Spacer(modifier = Modifier.height(14.dp))
    }
}
