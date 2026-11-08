package com.elevora.app.features.courses.model

import androidx.compose.ui.graphics.Color
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * Data models for the 6-Module, 24-Week, 72-Lesson Managerial English Programme
 */
data class CourseModuleData(
    val moduleNumber: Int,
    val title: String,
    val subtitle: String,
    val weekRange: String,
    val lessonRange: String,
    val totalLessons: Int = 12,
    val completedLessons: Int,
    val accentColor: Color,
    val outcomes: List<String>,
    val weeks: List<ModuleWeekInfo>
)

data class ModuleWeekInfo(
    val weekNumber: Int,
    val weekTitle: String,
    val lessonsSummary: String,
    val isCompleted: Boolean,
    val isLocked: Boolean
)

object ManagerialEnglishCurriculum {
    val modules: List<CourseModuleData> = listOf(
        CourseModuleData(
            moduleNumber = 1,
            title = "Building Confidence & Core Workplace English",
            subtitle = "Executive greetings, polite requests, active listening & tone",
            weekRange = "Weeks 1–4",
            lessonRange = "Lessons 1–12",
            completedLessons = 1,
            accentColor = ElevoraColors.GoldPrimary,
            outcomes = listOf(
                "Master professional self-introductions & corporate small talk",
                "Ask clarifying questions without sounding hesitant",
                "Express opinions assertively with diplomatic tact",
                "Deliver polite refusals and propose alternatives"
            ),
            weeks = listOf(
                ModuleWeekInfo(1, "Week 1: Professional Introductions & First Impressions", "Lessons 1–3 • Day 1 Active", true, false),
                ModuleWeekInfo(2, "Week 2: Clarifying & Confirming Information", "Lessons 4–6 • Interactive drills", false, false),
                ModuleWeekInfo(3, "Week 3: Expressing Opinions & Polite Disagreement", "Lessons 7–9 • Role-plays", false, false),
                ModuleWeekInfo(4, "Week 4: Month 1 Synthesis & Assessment", "Lessons 10–12 • Diagnostic speaking review", false, false)
            )
        ),
        CourseModuleData(
            moduleNumber = 2,
            title = "Workplace Conversation & Team Communication",
            subtitle = "Standups, cross-functional syncs, office diplomacy & rapport",
            weekRange = "Weeks 5–8",
            lessonRange = "Lessons 13–24",
            completedLessons = 0,
            accentColor = ElevoraColors.CyanCategory,
            outcomes = listOf(
                "Run crisp standup updates under 2 minutes",
                "Navigate cross-departmental collaboration smoothly",
                "Give and receive constructive peer feedback",
                "Build executive conversational presence"
            ),
            weeks = listOf(
                ModuleWeekInfo(5, "Week 5: Effective Daily Standups & Task Updates", "Lessons 13–15 • Conciseness drills", false, true),
                ModuleWeekInfo(6, "Week 6: Cross-functional Syncs with Tech & Ops", "Lessons 16–18 • Jargon management", false, true),
                ModuleWeekInfo(7, "Week 7: Constructive Feedback & Peer Alignment", "Lessons 19–21 • Tactful phrasing", false, true),
                ModuleWeekInfo(8, "Week 8: Month 2 Speaking Assessment", "Lessons 22–24 • Evaluated challenge", false, true)
            )
        ),
        CourseModuleData(
            moduleNumber = 3,
            title = "Managing People, Meetings & Performance",
            subtitle = "1-on-1 appraisals, meeting facilitation & conflict mediation",
            weekRange = "Weeks 9–12",
            lessonRange = "Lessons 25–36",
            completedLessons = 0,
            accentColor = ElevoraColors.PurpleCategory,
            outcomes = listOf(
                "Open, moderate, and synthesize high-stakes meetings",
                "Conduct quarterly performance reviews with empathy",
                "De-escalate workplace disagreements neutrally",
                "Delegate tasks with clear criteria and milestones"
            ),
            weeks = listOf(
                ModuleWeekInfo(9, "Week 9: Meeting Openings, Agendas & Facilitation", "Lessons 25–27 • Timekeeping phrases", false, true),
                ModuleWeekInfo(10, "Week 10: Performance Reviews & Goal Setting", "Lessons 28–30 • SMART criteria language", false, true),
                ModuleWeekInfo(11, "Week 11: Resolving Interpersonal Workplace Friction", "Lessons 31–33 • De-escalation scripts", false, true),
                ModuleWeekInfo(12, "Week 12: Mid-Course Assessment (Month 3)", "Lessons 34–36 • Comprehensive evaluation", false, true)
            )
        ),
        CourseModuleData(
            moduleNumber = 4,
            title = "Emails, Reports & High-Impact Presentations",
            subtitle = "Executive BLUF technique, data storytelling & slide delivery",
            weekRange = "Weeks 13–16",
            lessonRange = "Lessons 37–48",
            completedLessons = 0,
            accentColor = ElevoraColors.EmeraldCategory,
            outcomes = listOf(
                "Draft BLUF (Bottom Line Up Front) executive emails",
                "Summarize metrics into actionable 1-page briefs",
                "Deliver confident PowerPoint presentations",
                "Field challenging stakeholder Q&A questions"
            ),
            weeks = listOf(
                ModuleWeekInfo(13, "Week 13: Executive Email Architecture & Tone", "Lessons 37–39 • Subject lines & requests", false, true),
                ModuleWeekInfo(14, "Week 14: Report Summaries & Metric Narratives", "Lessons 40–42 • Translating charts", false, true),
                ModuleWeekInfo(15, "Week 15: Slide Presentation Flow & Transitions", "Lessons 43–45 • Verbal signposting", false, true),
                ModuleWeekInfo(16, "Week 16: Month 4 Speaking Assessment", "Lessons 46–48 • Pitch evaluation", false, true)
            )
        ),
        CourseModuleData(
            moduleNumber = 5,
            title = "Difficult Conversations, Complaints & Problem Solving",
            subtitle = "Managing escalations, client complaints & crisis communication",
            weekRange = "Weeks 17–20",
            lessonRange = "Lessons 49–60",
            completedLessons = 0,
            accentColor = ElevoraColors.CoralCategory,
            outcomes = listOf(
                "Handle angry clients and internal stakeholders calmly",
                "Apologize professionally without accepting undue legal liability",
                "Negotiate realistic deadlines during project crises",
                "Lead post-mortem problem-solving syncs"
            ),
            weeks = listOf(
                ModuleWeekInfo(17, "Week 17: Acknowledging Client Escalations", "Lessons 49–51 • Active calming scripts", false, true),
                ModuleWeekInfo(18, "Week 18: Explaining Complex Delays & Bottlenecks", "Lessons 52–54 • Accountability terms", false, true),
                ModuleWeekInfo(19, "Week 19: Collaborative Solution Finding", "Lessons 55–57 • Brainstorming leadership", false, true),
                ModuleWeekInfo(20, "Week 20: Month 5 Assessment", "Lessons 58–60 • Crisis simulation", false, true)
            )
        ),
        CourseModuleData(
            moduleNumber = 6,
            title = "Leadership, Fluency & Workplace Performance",
            subtitle = "Strategic vision, inspiring teams & executive graduation",
            weekRange = "Weeks 21–24",
            lessonRange = "Lessons 61–72",
            completedLessons = 0,
            accentColor = ElevoraColors.GoldAccent,
            outcomes = listOf(
                "Articulate strategic vision and quarterly mission",
                "Deliver spontaneous impromptus without hesitation",
                "Master nuanced professional humor and corporate culture",
                "Complete the Final Managerial English Certification"
            ),
            weeks = listOf(
                ModuleWeekInfo(21, "Week 21: Inspiring Vision & Townhall Addresses", "Lessons 61–63 • Keynote delivery", false, true),
                ModuleWeekInfo(22, "Week 22: Impromptu Speaking & Thinking on Feet", "Lessons 64–66 • The PREP method", false, true),
                ModuleWeekInfo(23, "Week 23: Complete Course Synthesis & Rehearsal", "Lessons 67–69 • Full scenario runs", false, true),
                ModuleWeekInfo(24, "Week 24: Final Certification Assessment", "Lessons 70–72 • Capstone graduation", false, true)
            )
        )
    )
}
