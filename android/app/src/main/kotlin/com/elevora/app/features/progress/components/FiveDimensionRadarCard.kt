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
import androidx.compose.material3.LinearProgressIndicator
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
import com.elevora.app.features.progress.model.SpeakingDimensionRating

/**
 * 5 Observation Dimensions (Fluency, Accuracy, Pronunciation, Vocabulary, Interaction)
 * specified in the official Trainer Guide and Course Syllabus
 */
@Composable
fun FiveDimensionRadarCard(
    ratings: List<SpeakingDimensionRating>,
    overallFluencyIndex: Float = 4.2f,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        ElevoraColors.SlateCard,
                        Color(0xFF101B32)
                    )
                )
            )
            .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "5-Dimension Speaking Evaluation",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Monthly assessment benchmark (Rated 1–5)",
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp
                    )
                }

                // Average Score Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "$overallFluencyIndex / 5.0",
                        color = ElevoraColors.GoldPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // The 5 Dimensions Rows
            ratings.forEach { rating ->
                val progressFraction = rating.scoreOutOf5 / 5.0f

                Column(modifier = Modifier.padding(vertical = 5.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(rating.accentColor)
                            )
                            Text(
                                text = rating.dimensionName,
                                color = ElevoraColors.TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = "${rating.scoreOutOf5} / 5",
                            color = rating.accentColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = rating.accentColor,
                        trackColor = Color(0xFF1B263C)
                    )

                    Text(
                        text = rating.description,
                        color = ElevoraColors.TextSecondary,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

        }
    }
}
