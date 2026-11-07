package com.elevora.app.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.core.designsystem.components.cards.ElevoraStatusBadge
import com.elevora.app.core.designsystem.components.progress.ElevoraCircularGauge

/**
 * Hero Progress Card on the Home Dashboard
 */
@Composable
fun HeroProgressCard(
    programmeName: String = "Managerial English",
    durationInfo: String = "6 Months • 72 Lessons",
    progress: Float = 0.18f,
    modifier: Modifier = Modifier
) {
    ElevoraGlassCard(
        modifier = modifier.fillMaxWidth(),
        cornerRadius = 20.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                ElevoraStatusBadge(text = "Your Programme")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = programmeName,
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = durationInfo,
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp
                )
            }

            ElevoraCircularGauge(
                percentage = progress,
                size = 68.dp,
                strokeWidth = 7.dp
            )
        }
    }
}

