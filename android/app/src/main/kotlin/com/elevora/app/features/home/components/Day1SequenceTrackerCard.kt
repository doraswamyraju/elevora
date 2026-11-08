package com.elevora.app.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * High-End Daily Milestone Showcase with Stepper Progress Nodes:
 * Highlights the active upcoming step (Step 5: Role-Play) with time estimate
 * and direct launch trigger.
 */
@Composable
fun Day1SequenceTrackerCard(
    currentStepIndex: Int = 4, // Step 5: Role-Play
    onStepClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        ElevoraColors.SlateCard,
                        Color(0xFF111E36)
                    )
                )
            )
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(24.dp))
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            
            // Header Row: Section Name + Step Counter Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Today's Learning Journey",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Day 1 of 5 • 7-Step Pedagogical Method",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Step 5 of 7",
                        color = ElevoraColors.GoldLight,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 7-Node Step Indicator Row (Connected Nodes)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until 7) {
                    val isDone = i < currentStepIndex
                    val isCurrent = i == currentStepIndex

                    Box(
                        modifier = Modifier
                            .size(if (isCurrent) 28.dp else 22.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isDone -> ElevoraColors.EmeraldCategory
                                    isCurrent -> ElevoraColors.GoldPrimary
                                    else -> Color(0xFF1E2B44)
                                }
                            )
                            .border(
                                width = if (isCurrent) 2.dp else 1.dp,
                                color = if (isCurrent) ElevoraColors.GoldLight else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { onStepClick(i) },
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            isDone -> Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            isCurrent -> Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = ElevoraColors.DeepNavy,
                                modifier = Modifier.size(15.dp)
                            )
                            else -> Text(
                                text = "${i + 1}",
                                color = ElevoraColors.TextMuted,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (i < 6) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(3.dp)
                                .padding(horizontal = 2.dp)
                                .background(
                                    if (i < currentStepIndex) ElevoraColors.EmeraldCategory
                                    else Color(0xFF1C273C)
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Active Step Highlight Card (Step 5: Role-Play)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF142442))
                    .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .clickable { onStepClick(currentStepIndex) }
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "NEXT UP",
                                color = ElevoraColors.GoldPrimary,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = "• ~4 mins",
                                color = ElevoraColors.TextSecondary,
                                fontSize = 10.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Step 5: Professional Self-Introduction",
                            color = ElevoraColors.TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "AI Role-Play with feedback & scoring",
                            color = ElevoraColors.TextSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(ElevoraColors.GoldPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Start Next Step",
                            tint = ElevoraColors.DeepNavy,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

        }
    }
}
