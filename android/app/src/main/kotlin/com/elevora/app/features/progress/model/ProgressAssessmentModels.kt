package com.elevora.app.features.progress.model

import androidx.compose.ui.graphics.Color
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * 5 Observation Dimensions rated 1–5 specified in the Trainer & Curriculum Package:
 * 1. Fluency
 * 2. Accuracy
 * 3. Pronunciation
 * 4. Vocabulary
 * 5. Interaction
 */
data class SpeakingDimensionRating(
    val dimensionName: String,
    val scoreOutOf5: Float,
    val description: String,
    val accentColor: Color
)

data class AssessmentMilestone(
    val id: String,
    val title: String,
    val stage: String,
    val weekTiming: String,
    val scorePercentage: Int?,
    val isCompleted: Boolean,
    val isUpcoming: Boolean
)

object ProgressAssessmentData {
    val dimensions = listOf(
        SpeakingDimensionRating(
            dimensionName = "Fluency",
            scoreOutOf5 = 4.2f,
            description = "Natural pacing, minimal hesitation, structured delivery",
            accentColor = ElevoraColors.GoldPrimary
        ),
        SpeakingDimensionRating(
            dimensionName = "Accuracy",
            scoreOutOf5 = 3.9f,
            description = "Grammar agreement, tense precision, preposition usage",
            accentColor = ElevoraColors.CyanCategory
        ),
        SpeakingDimensionRating(
            dimensionName = "Pronunciation",
            scoreOutOf5 = 4.5f,
            description = "Intonation, syllable stress, clarity of speech",
            accentColor = ElevoraColors.PurpleCategory
        ),
        SpeakingDimensionRating(
            dimensionName = "Vocabulary",
            scoreOutOf5 = 4.0f,
            description = "Executive managerial words & business collocations",
            accentColor = ElevoraColors.EmeraldCategory
        ),
        SpeakingDimensionRating(
            dimensionName = "Interaction",
            scoreOutOf5 = 4.3f,
            description = "Turn-taking, active listening markers & diplomatic tone",
            accentColor = ElevoraColors.CoralCategory
        )
    )

    val milestones = listOf(
        AssessmentMilestone(
            id = "m0",
            title = "Diagnostic Assessment",
            stage = "Baseline Entry Evaluation",
            weekTiming = "Class 1 (Completed)",
            scorePercentage = 68,
            isCompleted = true,
            isUpcoming = false
        ),
        AssessmentMilestone(
            id = "m1",
            title = "Month 1 Speaking Assessment",
            stage = "Module 1 Review • 5 Dimensions",
            weekTiming = "Week 4",
            scorePercentage = 84,
            isCompleted = true,
            isUpcoming = false
        ),
        AssessmentMilestone(
            id = "m2",
            title = "Month 2 Speaking Assessment",
            stage = "Module 2 Team Communication",
            weekTiming = "Week 8",
            scorePercentage = null,
            isCompleted = false,
            isUpcoming = true
        ),
        AssessmentMilestone(
            id = "m3",
            title = "Mid-Course Assessment",
            stage = "Half-Year Evaluation (Months 1–3)",
            weekTiming = "End of Month 3 (Week 12)",
            scorePercentage = null,
            isCompleted = false,
            isUpcoming = false
        ),
        AssessmentMilestone(
            id = "m4",
            title = "Month 4 Speaking Assessment",
            stage = "Module 4 Reporting & Presentations",
            weekTiming = "Week 16",
            scorePercentage = null,
            isCompleted = false,
            isUpcoming = false
        ),
        AssessmentMilestone(
            id = "m5",
            title = "Month 5 Speaking Assessment",
            stage = "Module 5 Difficult Conversations",
            weekTiming = "Week 20",
            scorePercentage = null,
            isCompleted = false,
            isUpcoming = false
        ),
        AssessmentMilestone(
            id = "m6",
            title = "Final Certification Assessment",
            stage = "Capstone Graduation (Lessons 71–72)",
            weekTiming = "Week 24",
            scorePercentage = null,
            isCompleted = false,
            isUpcoming = false
        )
    )
}
