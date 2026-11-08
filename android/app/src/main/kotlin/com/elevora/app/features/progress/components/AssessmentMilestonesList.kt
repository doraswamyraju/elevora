package com.elevora.app.features.progress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.progress.model.AssessmentMilestone

/**
 * Assessment Milestones Timeline (Diagnostic, 5 Monthly Assessments, Mid-Course, and Final)
 */
@Composable
fun AssessmentMilestonesList(
    milestones: List<AssessmentMilestone>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(ElevoraColors.SlateCard)
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Curriculum Milestones",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Weekly Quizzes & Formal Evaluations",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 11.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "2 of 7 Cleared",
                    color = ElevoraColors.GoldLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        milestones.forEachIndexed { index, m ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (m.isUpcoming) Color(0xFF13223E) else Color(0xFF0C1424))
                    .border(
                        1.dp,
                        if (m.isUpcoming) ElevoraColors.GoldPrimary.copy(alpha = 0.5f) else ElevoraColors.CardStroke,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    m.isCompleted -> ElevoraColors.EmeraldCategory
                                    m.isUpcoming -> ElevoraColors.GoldPrimary
                                    else -> Color(0xFF1B263C)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            m.isCompleted -> Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            m.isUpcoming -> Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = ElevoraColors.DeepNavy,
                                modifier = Modifier.size(16.dp)
                            )
                            else -> Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = ElevoraColors.TextMuted,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text(
                            text = m.title,
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${m.stage} • ${m.weekTiming}",
                            color = ElevoraColors.TextSecondary,
                            fontSize = 10.sp
                        )
                    }
                }

                if (m.scorePercentage != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ElevoraColors.EmeraldCategory.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "${m.scorePercentage}%",
                            color = ElevoraColors.EmeraldCategory,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                } else if (m.isUpcoming) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(ElevoraColors.GoldPrimary)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Next",
                            color = ElevoraColors.DeepNavy,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            if (index < milestones.size - 1) {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
