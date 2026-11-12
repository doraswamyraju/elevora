package com.elevora.app.core.navigation.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.navigation.topbar.items.TopBarBackAction
import com.elevora.app.core.navigation.topbar.items.TopBarNotificationBell

/**
 * Luxury Elevora TopBar Shell:
 * Displays official Brand Banner on Home, Streak Badge, Notifications & Profile Action
 */
@Composable
fun ElevoraTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    showBackButton: Boolean = false,
    showSidebarToggle: Boolean = true,
    showBrandLogo: Boolean = true,
    showProfileIcon: Boolean = true,
    showNotificationBell: Boolean = true,
    showLogoutButton: Boolean = false,
    streakDays: Int = 4,
    userName: String = "Raju",
    onBackClick: () -> Unit = {},
    onSidebarToggleClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = 1.dp,
                color = ElevoraColors.CardStroke,
                shape = androidx.compose.ui.graphics.RectangleShape
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side: Sidebar Toggle / Back Button + Brand Logo / Title
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                TopBarBackAction(onBack = onBackClick)
                Spacer(modifier = Modifier.width(10.dp))
            } else if (showSidebarToggle) {
                IconButton(
                    onClick = onSidebarToggleClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ElevoraColors.SlateCard)
                        .border(1.dp, ElevoraColors.CardStroke, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Toggle Navigation Drawer",
                        tint = ElevoraColors.GoldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }

            if (showBrandLogo && title == null) {
                Image(
                    painter = painterResource(id = R.drawable.elevora_logo_banner),
                    contentDescription = "Elevora Logo",
                    modifier = Modifier
                        .height(30.dp)
                        .width(120.dp)
                )
            } else if (title != null) {
                Text(
                    text = title,
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        // Right side: Streak pill + Notification Bell + User Profile Avatar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (showBrandLogo) {
                // Live streak badge on Home
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFEF3C7))
                        .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🔥", fontSize = 11.sp)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "$streakDays",
                            color = ElevoraColors.GoldAmber,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            if (showNotificationBell) {
                TopBarNotificationBell(onClick = onNotificationClick)
            }

            if (showProfileIcon) {
                // User Initial Profile Avatar with Oxford Blue Rim
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF6FF))
                        .border(1.5.dp, ElevoraColors.GoldPrimary, CircleShape)
                        .clickable(onClick = onProfileClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userName.take(1).uppercase(),
                        color = ElevoraColors.GoldPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }

            if (showLogoutButton) {
                IconButton(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(ElevoraColors.SlateCard)
                        .border(1.dp, ElevoraColors.AlertAmber.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = "Log Out",
                        tint = ElevoraColors.AlertAmber,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}
