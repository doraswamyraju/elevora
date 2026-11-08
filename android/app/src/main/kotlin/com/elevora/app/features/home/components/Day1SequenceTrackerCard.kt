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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
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

/**
 * 7-Step Day 1 Pedagogical Sequence Tracker Card on the Home Screen
 */
@Composable
fun Day1SequenceTrackerCard(
    currentStepIndex: Int = 4, // 0-based: Step 4 is Role-play
    onStepClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val steps = listOf(
        "1. Video: Professional Introductions",
        "2. Words for Work (Key Vocabulary)",
        "3. Language Focus (Grammar in Action)",
        "4. Controlled Practice (Sentence Builders)",
        "5. Role-Play: Self-Introduction (Dual Mode)",
        "6. Quiz: Check Understanding",
        "7. Day 1 Completed & Badges"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ElevoraColors.SlateCard)
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Today's Learning Path",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Day 1 of 5 • 7-Step Method",
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
                    text = "Step 5 of 7",
                    color = ElevoraColors.GoldLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        steps.forEachIndexed { index, stepTitle ->
            val isCompleted = index < currentStepIndex
            val isCurrent = index == currentStepIndex
            val isLocked = index > currentStepIndex

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isCurrent) Color(0xFF162544) else Color.Transparent)
                    .clickable { onStepClick(index) }
                    .padding(horizontal = 10.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Status icon circle
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isCompleted -> ElevoraColors.EmeraldCategory.copy(alpha = 0.2f)
                                    isCurrent -> ElevoraColors.GoldPrimary.copy(alpha = 0.25f)
                                    else -> ElevoraColors.MidnightSurface
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            isCompleted -> Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = ElevoraColors.EmeraldCategory,
                                modifier = Modifier.size(14.dp)
                            )
                            isCurrent -> Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = ElevoraColors.GoldPrimary,
                                modifier = Modifier.size(14.dp)
                            )
                            else -> Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = ElevoraColors.TextSecondary.copy(alpha = 0.5f),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = stepTitle,
                        color = when {
                            isCurrent -> ElevoraColors.GoldLight
                            isCompleted -> ElevoraColors.TextPrimary
                            else -> ElevoraColors.TextSecondary.copy(alpha = 0.6f)
                        },
                        fontSize = 12.sp,
                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
                    )
                }

                if (isCurrent) {
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
                } else {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = ElevoraColors.CardStroke,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if (index < steps.size - 1) {
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}
