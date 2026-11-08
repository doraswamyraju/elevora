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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

data class WeekDayLesson(
    val dayNumber: Int,
    val title: String,
    val subtitle: String,
    val isCurrent: Boolean = false,
    val isLocked: Boolean = false,
    val progressPercent: Int = 0
)

val week1ActualDays = listOf(
    WeekDayLesson(
        dayNumber = 1,
        title = "Professional Introductions",
        subtitle = "Role, department, experience, responsibility & opening phrases",
        isCurrent = true,
        progressPercent = 0
    ),
    WeekDayLesson(
        dayNumber = 2,
        title = "Job Roles & Responsibilities",
        subtitle = "Manage, supervise, coordinate, oversee & reporting structures",
        isLocked = true
    ),
    WeekDayLesson(
        dayNumber = 3,
        title = "Present Simple for Work",
        subtitle = "Daily managerial routines, schedules, monitoring & reviews",
        isLocked = true
    )
)

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
                    text = "Module 1 • 3 Classes per Week",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Week $selectedWeek: Professional Introductions",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
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
                        .padding(vertical = 10.dp),
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

        Text(
            text = "CLASSES IN WEEK $selectedWeek",
            color = ElevoraColors.TextMuted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Weekly routine: Class 1 = Learn | Class 2 = Practise | Class 3 = Apply",
            color = ElevoraColors.TextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
        )

        // Day Lessons List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(week1ActualDays) { day ->
                val isHighlighted = day.isCurrent
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
                        .clickable(enabled = !day.isLocked) { onDayClick(day.dayNumber) }
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
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
                                    modifier = Modifier.size(18.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = "Play",
                                    tint = ElevoraColors.GoldPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Day ${day.dayNumber} • Class ${day.dayNumber}",
                                color = if (isHighlighted) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = day.title,
                                color = if (day.isLocked) ElevoraColors.TextMuted else ElevoraColors.TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = day.subtitle,
                                color = ElevoraColors.TextSecondary,
                                fontSize = 12.sp,
                                maxLines = 1
                            )
                        }

                        if (isHighlighted) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, ElevoraColors.GoldPrimary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${day.progressPercent}%",
                                    color = ElevoraColors.GoldPrimary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
