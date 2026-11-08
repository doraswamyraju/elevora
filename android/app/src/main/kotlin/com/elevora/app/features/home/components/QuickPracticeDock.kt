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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * 4-Tile Quick Practice Dock for instant access to Speaking, Quiz, Notes, and Progress
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
        Text(
            text = "Quick Practice",
            color = ElevoraColors.TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            QuickPracticeTile(
                title = "AI Tutor",
                icon = Icons.Outlined.Mic,
                iconTint = ElevoraColors.PurpleCategory,
                onClick = onSpeakingClick,
                modifier = Modifier.weight(1f)
            )
            QuickPracticeTile(
                title = "Practice",
                icon = Icons.Outlined.MenuBook,
                iconTint = ElevoraColors.GoldPrimary,
                onClick = onVocabClick,
                modifier = Modifier.weight(1f)
            )
            QuickPracticeTile(
                title = "Quiz",
                icon = Icons.Outlined.Checklist,
                iconTint = ElevoraColors.CyanCategory,
                onClick = onQuizClick,
                modifier = Modifier.weight(1f)
            )
            QuickPracticeTile(
                title = "Progress",
                icon = Icons.Outlined.Leaderboard,
                iconTint = ElevoraColors.EmeraldCategory,
                onClick = onProgressClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun QuickPracticeTile(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(ElevoraColors.SurfaceNavy)
            .border(1.dp, ElevoraColors.CardBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconTint.copy(alpha = 0.15f))
                    .border(1.dp, iconTint.copy(alpha = 0.35f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                color = ElevoraColors.TextPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
