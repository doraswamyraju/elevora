package com.elevora.app.core.navigation.topbar.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * TopBar Notification Bell with unread amber dot
 */
@Composable
fun TopBarNotificationBell(
    hasUnread: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ElevoraColors.SlateCard)
                .border(1.dp, ElevoraColors.CardStroke, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = ElevoraColors.TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        if (hasUnread) {
            Box(
                modifier = Modifier
                    .offset(x = (-3).dp, y = 3.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.GoldPrimary)
            )
        }
    }
}

