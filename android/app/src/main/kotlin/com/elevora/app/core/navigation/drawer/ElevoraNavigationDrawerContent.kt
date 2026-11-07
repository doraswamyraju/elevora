package com.elevora.app.core.navigation.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.navigation.bottombar.BottomTab

@Composable
fun ElevoraNavigationDrawerContent(
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onCloseDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(ElevoraColors.MidnightSurface)
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        // Drawer Header with Profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onProfileClick()
                    onCloseDrawer()
                }
                .padding(vertical = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.elevora_logo_square),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(2.dp, ElevoraColors.GoldPrimary, CircleShape)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Priya Sharma",
                    color = ElevoraColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "View Profile & Settings",
                    color = ElevoraColors.GoldPrimary,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = ElevoraColors.CardStroke)
        Spacer(modifier = Modifier.height(16.dp))

        // Navigation Items
        DrawerItem(
            icon = Icons.Outlined.Home,
            label = "Home Dashboard",
            isSelected = currentTab == BottomTab.HOME,
            onClick = {
                onTabSelected(BottomTab.HOME)
                onCloseDrawer()
            }
        )

        DrawerItem(
            icon = Icons.Outlined.School,
            label = "Learning Path",
            isSelected = currentTab == BottomTab.LEARN,
            onClick = {
                onTabSelected(BottomTab.LEARN)
                onCloseDrawer()
            }
        )

        DrawerItem(
            icon = Icons.Outlined.Mic,
            label = "Speaking Practice",
            isSelected = currentTab == BottomTab.PRACTICE,
            onClick = {
                onTabSelected(BottomTab.PRACTICE)
                onCloseDrawer()
            }
        )

        DrawerItem(
            icon = Icons.Outlined.Assessment,
            label = "Assessments & Progress",
            isSelected = currentTab == BottomTab.ASSESS,
            onClick = {
                onTabSelected(BottomTab.ASSESS)
                onCloseDrawer()
            }
        )

        DrawerItem(
            icon = Icons.Outlined.Book,
            label = "Course Catalogue",
            isSelected = currentTab == BottomTab.COURSES,
            onClick = {
                onTabSelected(BottomTab.COURSES)
                onCloseDrawer()
            }
        )

        DrawerItem(
            icon = Icons.Outlined.Settings,
            label = "Profile Settings",
            isSelected = false,
            onClick = {
                onProfileClick()
                onCloseDrawer()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(color = ElevoraColors.CardStroke)
        Spacer(modifier = Modifier.height(14.dp))

        // Logout action at the bottom of the drawer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onLogoutClick()
                    onCloseDrawer()
                }
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = "Log Out",
                tint = ElevoraColors.AlertAmber,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Log Out",
                color = ElevoraColors.AlertAmber,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun DrawerItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) ElevoraColors.GoldSubtle else androidx.compose.ui.graphics.Color.Transparent
    val contentColor = if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.TextSecondary
    val shape = RoundedCornerShape(12.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = label,
            color = if (isSelected) ElevoraColors.TextPrimary else ElevoraColors.TextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}

