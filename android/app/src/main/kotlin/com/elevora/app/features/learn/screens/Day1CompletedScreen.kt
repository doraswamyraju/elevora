package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton

/**
 * Screen matching Screenshot 10: Day 1 Completed celebration with stats and Back to Week 1
 */
@Composable
fun Day1CompletedScreen(
    onContinueToDay2: () -> Unit = {},
    onBackToWeek1: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        // Gold Medal Badge Badge Container
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(ElevoraColors.GoldPrimary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Completed",
                tint = ElevoraColors.DeepNavy,
                modifier = Modifier.size(54.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Day 1 Completed!",
            color = ElevoraColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Great start! You've completed Introduction & Goals.",
            color = ElevoraColors.TextSecondary,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Stats Row: 5/5 Activities | 100% Score | +50 Points
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CompletionStatCard(
                modifier = Modifier.weight(1f),
                icon = "📚",
                value = "5 / 5",
                label = "Activities"
            )
            CompletionStatCard(
                modifier = Modifier.weight(1f),
                icon = "🏆",
                value = "100%",
                label = "Score"
            )
            CompletionStatCard(
                modifier = Modifier.weight(1f),
                icon = "⭐",
                value = "+50",
                label = "Points"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ElevoraPrimaryButton(
            text = "Continue to Day 2 →",
            onClick = onContinueToDay2
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Secondary Button: Back to Week 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(ElevoraColors.SlateCard)
                .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(26.dp))
                .clickable(onClick = onBackToWeek1)
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "← Back to Week 1",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun CompletionStatCard(
    icon: String,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(ElevoraColors.SlateCardGlass)
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(14.dp))
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = icon, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = ElevoraColors.GoldPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                color = ElevoraColors.TextSecondary,
                fontSize = 11.sp
            )
        }
    }
}
