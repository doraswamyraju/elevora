package com.elevora.app.features.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.courses.components.CurriculumProgrammeHeroCard
import com.elevora.app.features.courses.components.ModuleAccordionCard
import com.elevora.app.features.courses.model.ManagerialEnglishCurriculum

/**
 * 6-Module, 24-Week, 72-Lesson Course Syllabus Screen
 */
@Composable
fun CourseCatalogueScreen(
    onModuleSelect: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val modules = ManagerialEnglishCurriculum.modules

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // 1. Programme Hero Header Card
        CurriculumProgrammeHeroCard(
            totalModules = 6,
            totalWeeks = 24,
            totalLessons = 72
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 2. Section Heading
        Text(
            text = "6 Core Learning Modules",
            color = ElevoraColors.TextPrimary,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Structured 24-week progression from core foundations to executive leadership",
            color = ElevoraColors.TextSecondary,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 3. The 6 Module Cards
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            modules.forEach { moduleData ->
                ModuleAccordionCard(
                    moduleData = moduleData,
                    onStartModuleClick = { onModuleSelect(moduleData.moduleNumber) }
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom bar
    }
}
