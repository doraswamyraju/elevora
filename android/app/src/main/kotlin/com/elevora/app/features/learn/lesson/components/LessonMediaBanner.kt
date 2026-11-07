package com.elevora.app.features.learn.lesson.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors

@Composable
fun LessonMediaBanner(
    durationText: String = "05:24",
    onPlayClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(18.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(cardShape)
            .background(ElevoraColors.SlateCard),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.elevora_logo_square),
            contentDescription = "Lesson Media",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Dark dim overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(ElevoraColors.DarkVoid.copy(alpha = 0.55f))
        )

        // Play Button with Gold Gradient
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(ElevoraColors.GoldButtonGradient)
                .clickable(onClick = onPlayClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play Video/Audio",
                tint = ElevoraColors.DarkVoid,
                modifier = Modifier.size(32.dp)
            )
        }

        // Duration Badge
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(ElevoraColors.DarkVoid.copy(alpha = 0.8f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = durationText,
                color = ElevoraColors.TextPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

