package com.elevora.app.features.courses.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
 * Top Programme summary statistics card (6 Modules • 24 Weeks • 72 Lessons)
 */
@Composable
fun CurriculumProgrammeHeroCard(
    programmeTitle: String = "Managerial English Development Programme",
    totalModules: Int = 6,
    totalWeeks: Int = 24,
    totalLessons: Int = 72,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF101C36),
                        ElevoraColors.SlateCard
                    )
                )
            )
            .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "FLAGSHIP PROFESSIONAL CURRICULUM",
                    color = ElevoraColors.GoldLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = programmeTitle,
                color = ElevoraColors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 22.sp
            )

            Text(
                text = "Learn → Understand → Practise → Apply → Assess → Improve",
                color = ElevoraColors.TextSecondary,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3 Key Metrics Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CurriculumMetricPill(
                    label = "Modules",
                    value = "$totalModules",
                    accentColor = ElevoraColors.GoldPrimary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                CurriculumMetricPill(
                    label = "Duration",
                    value = "$totalWeeks Weeks",
                    accentColor = ElevoraColors.CyanCategory,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                CurriculumMetricPill(
                    label = "Total Lessons",
                    value = "$totalLessons Lessons",
                    accentColor = ElevoraColors.EmeraldCategory,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun CurriculumMetricPill(
    label: String,
    value: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0B1426))
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                color = accentColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = ElevoraColors.TextSecondary,
                fontSize = 10.sp
            )
        }
    }
}
