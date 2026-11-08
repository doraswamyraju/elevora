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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

data class DayItem(
    val dayNumber: Int,
    val title: String,
    val isCompleted: Boolean = false,
    val isCurrent: Boolean = false,
    val isLocked: Boolean = false,
    val progressPercent: Int = 0
)

val week1Days = listOf(
    DayItem(1, "Introduction & Goals", isCurrent = true, progressPercent = 0),
    DayItem(2, "Professional Introductions", isLocked = true),
    DayItem(3, "Talking About Your Work", isLocked = true),
    DayItem(4, "Workplace Vocabulary (Part 1)", isLocked = true),
    DayItem(5, "Small Talk at Work", isLocked = true)
)

/**
 * Screen matching Screenshot 3: Module 1 - Week 1 with Week tab pills + Day 1-5 list
 */
@Composable
fun ModuleWeekScreen(
    onBackClick: () -> Unit = {},
    onDayClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedWeek by remember { mutableStateOf(1) }

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
                text = "Module 1 – Week $selectedWeek",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Horizontal Week Tabs (Week 1, Week 2, Week 3, Week 4)
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            (1..4).forEach { weekNum ->
                val isSelected = selectedWeek == weekNum
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.SlateCard)
                        .border(
                            1.dp,
                            if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedWeek = weekNum }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Week $weekNum",
                        color = if (isSelected) ElevoraColors.DeepNavy else ElevoraColors.TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        // Section Title
        Text(
            text = "Week $selectedWeek",
            color = ElevoraColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Getting Started & Everyday English",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
        )

        // Day Lessons List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(week1Days) { day ->
                val isHighlighted = day.isCurrent
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
                        .clickable(enabled = !day.isLocked) {
                            onDayClick(day.dayNumber)
                        }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Play Icon or Lock Icon
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
                            if (day.isLocked) {
                                Icon(
                                    imageVector = Icons.Outlined.Lock,
                                    contentDescription = "Locked",
                                    tint = ElevoraColors.TextMuted,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = "Play",
                                    tint = ElevoraColors.GoldPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Day ${day.dayNumber}",
                                color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.TextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = day.title,
                                color = if (day.isLocked) ElevoraColors.TextMuted else ElevoraColors.TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        if (isHighlighted) {
                            // Circular 0% progress indicator
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, ElevoraColors.GoldPrimary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${day.progressPercent}%",
                                    color = ElevoraColors.GoldPrimary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else if (day.isLocked) {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = null,
                                tint = ElevoraColors.TextMuted,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
