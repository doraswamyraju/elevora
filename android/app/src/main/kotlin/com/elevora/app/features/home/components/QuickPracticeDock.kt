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
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * 2x2 Grid of Luxury Quick Practice Cards with micro-stats,
 * glowing icons, and high-impact typography
 */
@Composable
fun QuickPracticeDock(
    onSpeakingClick: () -> Unit = {},
    onQuizClick: () -> Unit = {},
    onVocabClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Quick Practice",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )

            Text(
                text = "Bite-sized • 5 min",
                color = ElevoraColors.GoldLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 1: AI Speaking Tutor & Vocabulary Lab
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ExecutivePracticeTile(
                title = "AI Speaking Tutor",
                subtitle = "Live role-play & fluency",
                badgeText = "24/7 AI",
                badgeColor = ElevoraColors.PurpleCategory,
                icon = Icons.Outlined.Mic,
                iconTint = ElevoraColors.PurpleCategory,
                onClick = onSpeakingClick,
                modifier = Modifier.weight(1f)
            )

            ExecutivePracticeTile(
                title = "Vocabulary Lab",
                subtitle = "10 essential work words",
                badgeText = "Day 1",
                badgeColor = ElevoraColors.GoldPrimary,
                icon = Icons.AutoMirrored.Outlined.MenuBook,
                iconTint = ElevoraColors.GoldPrimary,
                onClick = onVocabClick,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2: Daily Check Quiz & Progress Analytics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ExecutivePracticeTile(
                title = "Check Quiz",
                subtitle = "5 quick understanding checks",
                badgeText = "3 min",
                badgeColor = ElevoraColors.CyanCategory,
                icon = Icons.Outlined.Checklist,
                iconTint = ElevoraColors.CyanCategory,
                onClick = onQuizClick,
                modifier = Modifier.weight(1f)
            )

            ExecutivePracticeTile(
                title = "Fluency Metrics",
                subtitle = "Score: 85% • +48% gain",
                badgeText = "Leaderboard",
                badgeColor = ElevoraColors.EmeraldCategory,
                icon = Icons.Outlined.Leaderboard,
                iconTint = ElevoraColors.EmeraldCategory,
                onClick = onProgressClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ExecutivePracticeTile(
    title: String,
    subtitle: String,
    badgeText: String,
    badgeColor: Color,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFF8FAFC)
                    )
                )
            )
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Column {
            // Icon + Badge Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(iconTint.copy(alpha = 0.15f))
                        .border(1.dp, iconTint.copy(alpha = 0.35f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconTint,
                        modifier = Modifier.size(19.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(badgeColor.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badgeText,
                        color = badgeColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                color = ElevoraColors.TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                color = ElevoraColors.TextSecondary,
                fontSize = 10.sp,
                maxLines = 1,
                lineHeight = 13.sp
            )
        }
    }
}
