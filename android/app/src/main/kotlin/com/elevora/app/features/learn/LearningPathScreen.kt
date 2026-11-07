package com.elevora.app.features.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.learn.components.ModuleCard

data class ModuleItem(
    val number: Int,
    val title: String,
    val completedLessons: Int,
    val totalLessons: Int = 12
)

val managerialEnglishModules = listOf(
    ModuleItem(1, "Building Confidence & Core Workplace English", 3, 12),
    ModuleItem(2, "Workplace Conversation & Team Communication", 0, 12),
    ModuleItem(3, "Managing People, Meetings & Performance", 0, 12),
    ModuleItem(4, "Emails, Reports & Presentations", 0, 12),
    ModuleItem(5, "Difficult Conversations, Complaints & Problem Solving", 0, 12),
    ModuleItem(6, "Leadership, Fluency & Workplace Performance", 0, 12)
)

/**
 * Learning Path Screen displaying the 6 flagship Managerial English Modules
 */
@Composable
fun LearningPathScreen(
    onModuleClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(managerialEnglishModules) { index, module ->
            ModuleCard(
                moduleNumber = module.number,
                moduleTitle = module.title,
                completedLessons = module.completedLessons,
                totalLessons = module.totalLessons,
                onClick = { onModuleClick(module.number) }
            )
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

