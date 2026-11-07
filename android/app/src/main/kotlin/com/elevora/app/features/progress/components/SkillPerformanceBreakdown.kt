package com.elevora.app.features.progress.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Spellcheck
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard

@Composable
fun SkillPerformanceBreakdown(modifier: Modifier = Modifier) {
    ElevoraGlassCard(modifier = modifier.fillMaxWidth(), cornerRadius = 16.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(
                text = "Skill Performance",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            SkillRow(icon = Icons.Outlined.Mic, skillName = "Speaking", percentage = 72)
            SkillRow(icon = Icons.Outlined.MenuBook, skillName = "Vocabulary", percentage = 68)
            SkillRow(icon = Icons.Outlined.Spellcheck, skillName = "Grammar", percentage = 70)
            SkillRow(icon = Icons.Outlined.Headphones, skillName = "Listening", percentage = 65)
            SkillRow(icon = Icons.Outlined.Edit, skillName = "Writing", percentage = 62)
        }
    }
}

@Composable
private fun SkillRow(
    icon: ImageVector,
    skillName: String,
    percentage: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ElevoraColors.GoldPrimary,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "  $skillName",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )
        LinearProgressIndicator(
            progress = { percentage / 100f },
            modifier = Modifier.weight(1.5f).height(6.dp),
            color = ElevoraColors.GoldPrimary,
            trackColor = ElevoraColors.CardStroke
        )
        Text(
            text = "  $percentage%",
            color = ElevoraColors.TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

