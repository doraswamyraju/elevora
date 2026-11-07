package com.elevora.app.core.designsystem.components.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * Executive Circular Progress Ring
 */
@Composable
fun ElevoraCircularGauge(
    percentage: Float,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    strokeWidth: Dp = 6.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val strokePx = strokeWidth.toPx()
            
            // Background track
            drawCircle(
                color = ElevoraColors.CardStroke,
                style = Stroke(width = strokePx)
            )

            // Progress arc
            val sweepAngle = (percentage.coerceIn(0f, 1f)) * 360f
            drawArc(
                brush = ElevoraColors.GoldButtonGradient,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokePx, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${(percentage * 100).toInt()}%",
            color = ElevoraColors.TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value * 0.28).sp
        )
    }
}

