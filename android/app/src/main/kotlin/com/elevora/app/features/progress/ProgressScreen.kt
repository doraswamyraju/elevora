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
import com.elevora.app.features.progress.components.AssessmentMilestonesList
import com.elevora.app.features.progress.components.FiveDimensionRadarCard
import com.elevora.app.features.progress.model.ProgressAssessmentData

/**
 * Complete Assessment & Progress Screen
 * - Overall Programme Completion Gauge
 * - 5 Observation Dimensions (Fluency, Accuracy, Pronunciation, Vocabulary, Interaction)
 * - Assessment Milestones (Diagnostic, 5 Monthly, Mid-Course, Final)
 */
@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier
) {
    val dimensions = ProgressAssessmentData.dimensions
    val milestones = ProgressAssessmentData.milestones

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // 1. Overall Programme Progress Card
        ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 22.dp) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevoraCircularGauge(
                    percentage = 0.22f,
                    size = 76.dp,
                    strokeWidth = 8.dp
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = "Programme Progress",
                        color = ElevoraColors.TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "16 of 72 lessons completed (Week 6)",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ElevoraStatusBadge(
                        text = "✓ On Track • High Fluency Pace",
                        backgroundColor = ElevoraColors.SuccessGreenSubtle,
                        textColor = ElevoraColors.SuccessGreen
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 2. 5 Observation Dimensions Rating Card (Fluency, Accuracy, Pronunciation, Vocab, Interaction)
        FiveDimensionRadarCard(
            ratings = dimensions,
            overallFluencyIndex = 4.2f
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 3. Curriculum Assessment Milestones (Diagnostic, Monthly 1–5, Mid-Course, Final)
        AssessmentMilestonesList(
            milestones = milestones
        )

        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom bar
    }
}
