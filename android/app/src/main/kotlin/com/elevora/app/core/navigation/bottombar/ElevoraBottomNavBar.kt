package com.elevora.app.core.navigation.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.navigation.bottombar.items.NavItemAssess
import com.elevora.app.core.navigation.bottombar.items.NavItemCourses
import com.elevora.app.core.navigation.bottombar.items.NavItemHome
import com.elevora.app.core.navigation.bottombar.items.NavItemLearn
import com.elevora.app.core.navigation.bottombar.items.NavItemPractice

enum class BottomTab {
    HOME, LEARN, PRACTICE, ASSESS, COURSES
}

/**
 * Elevora Bottom Navigation Bar Shell Container
 */
@Composable
fun ElevoraBottomNavBar(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
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
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItemHome(
            isSelected = selectedTab == BottomTab.HOME,
            onClick = { onTabSelected(BottomTab.HOME) }
        )
        NavItemLearn(
            isSelected = selectedTab == BottomTab.LEARN,
            onClick = { onTabSelected(BottomTab.LEARN) }
        )
        NavItemPractice(
            isSelected = selectedTab == BottomTab.PRACTICE,
            onClick = { onTabSelected(BottomTab.PRACTICE) }
        )
        NavItemAssess(
            isSelected = selectedTab == BottomTab.ASSESS,
            onClick = { onTabSelected(BottomTab.ASSESS) }
        )
        NavItemCourses(
            isSelected = selectedTab == BottomTab.COURSES,
            onClick = { onTabSelected(BottomTab.COURSES) }
        )
    }
}

