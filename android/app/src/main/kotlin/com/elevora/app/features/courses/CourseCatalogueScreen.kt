package com.elevora.app.features.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.courses.components.CourseCard

@Composable
fun CourseCatalogueScreen(
    onCourseClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            CourseCard(
                title = "Managerial English",
                description = "Master workplace conversations, team communication, and leadership dialogues.",
                isFeatured = true,
                onClick = { onCourseClick("managerial_english") }
            )
        }
        item {
            CourseCard(
                title = "Business Communication",
                description = "Improve your day-to-day workplace emails, reports, and team interactions.",
                onClick = { onCourseClick("business_comm") }
            )
        }
        item {
            CourseCard(
                title = "Leadership Skills",
                description = "Develop people management, performance discussions, and leadership presence.",
                onClick = { onCourseClick("leadership") }
            )
        }
        item {
            CourseCard(
                title = "Presentation Skills",
                description = "Plan, design and deliver high-impact executive presentations.",
                onClick = { onCourseClick("presentations") }
            )
        }
        item {
            CourseCard(
                title = "Productivity & Time Management",
                description = "Get more done with practical workplace prioritization frameworks.",
                onClick = { onCourseClick("productivity") }
            )
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

