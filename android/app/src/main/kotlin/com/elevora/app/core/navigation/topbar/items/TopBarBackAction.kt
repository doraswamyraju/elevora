package com.elevora.app.core.navigation.topbar.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * TopBar Circular Back Action Button
 */
@Composable
fun TopBarBackAction(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onBack,
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ElevoraColors.SlateCard)
            .border(1.dp, ElevoraColors.CardStroke, CircleShape)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Navigate Back",
            tint = ElevoraColors.TextPrimary,
            modifier = Modifier.size(18.dp)
        )
    }
}

