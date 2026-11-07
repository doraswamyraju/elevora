package com.elevora.app.features.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.core.designsystem.components.cards.ElevoraStatusBadge
import com.elevora.app.core.designsystem.components.progress.ElevoraCircularGauge
import com.elevora.app.features.progress.components.SkillPerformanceBreakdown

@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 20.dp) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevoraCircularGauge(percentage = 0.18f, size = 76.dp, strokeWidth = 8.dp)

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = "Programme Progress",
                        color = ElevoraColors.TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "13 of 72 lessons completed",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ElevoraStatusBadge(
                        text = "✓ On Track",
                        backgroundColor = ElevoraColors.SuccessGreenSubtle,
                        textColor = ElevoraColors.SuccessGreen
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SkillPerformanceBreakdown()

        Spacer(modifier = Modifier.height(80.dp))
    }
}

