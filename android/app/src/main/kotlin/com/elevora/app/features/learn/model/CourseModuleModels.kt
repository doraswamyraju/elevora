package com.elevora.app.features.learn.model

data class CourseModule(
    val id: Int,
    val title: String,
    val description: String,
    val totalWeeks: Int = 4,
    val totalLessons: Int = 12,
    val level: String = "Beginner",
    val completedLessons: Int = 3
)

data class WeekPlan(
    val weekNumber: Int,
    val title: String,
    val isCurrent: Boolean = false,
    val isLocked: Boolean = false,
    val days: List<DayLessonSummary>
)

data class DayLessonSummary(
    val dayNumber: Int,
    val title: String,
    val isLocked: Boolean = false,
    val isCompleted: Boolean = false,
    val progressPercent: Int = 0
)

data class LessonStepItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val durationText: String,
    val isCompleted: Boolean = false,
    val isCurrent: Boolean = false
)

data class KeyExpression(
    val phrase: String,
    val audioUrl: String? = null
)

data class FillInBlankQuestion(
    val id: Int,
    val promptBefore: String,
    val promptAfter: String,
    val options: List<String>,
    val correctAnswer: String
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)

data class Day1FullContent(
    val moduleNumber: Int = 1,
    val weekNumber: Int = 1,
    val dayNumber: Int = 1,
    val title: String = "Introduction & Goals",
    val subtitle: String = "Welcome to Elevora! Today, you'll understand the course, set your goals and learn key expressions.",
    val videoTitle: String = "Course Overview & Learning Goals",
    val videoDuration: String = "5:14",
    val overviewPoints: List<String> = listOf(
        "Understand what you will learn in Module 1",
        "Know how the 4-week plan works (3 classes per week)",
        "Set your personal learning goals & targets",
        "Learn and pronounce key expressions for getting started"
    ),
    val keyExpressions: List<String> = listOf(
        "I'm looking forward to learning.",
        "This course will help me at work.",
        "My goal is to improve my professional communication.",
        "I want to be more confident in meetings.",
        "I can practise a little every day."
    ),
    val speakRepeatSentence: String = "I'm looking forward to learning.",
    val quickActivityQuestions: List<FillInBlankQuestion> = listOf(
        FillInBlankQuestion(
            id = 1,
            promptBefore = "My goal is to ",
            promptAfter = " my communication skills.",
            options = listOf("improve", "moves", "change"),
            correctAnswer = "improve"
        ),
        FillInBlankQuestion(
            id = 2,
            promptBefore = "I'm ",
            promptAfter = " forward to learning.",
            options = listOf("look", "looking", "looked"),
            correctAnswer = "looking"
        ),
        FillInBlankQuestion(
            id = 3,
            promptBefore = "This course will ",
            promptAfter = " me at work.",
            options = listOf("help", "helps", "helping"),
            correctAnswer = "help"
        )
    ),
    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            question = "What is the main goal of Module 1?",
            options = listOf(
                "To improve professional English communication",
                "To learn advanced grammar only",
                "To prepare for a written school exam",
                "To write long academic reports"
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 2,
            question = "How many classes are planned per week?",
            options = listOf("1 class", "3 classes", "5 classes", "7 classes"),
            correctIndex = 1
        ),
        QuizQuestion(
            id = 3,
            question = "Which phrase is used to start your professional introduction?",
            options = listOf(
                "My main responsibility is...",
                "I am leaving early today.",
                "Let us cancel the agenda.",
                "None of the above"
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 4,
            question = "What should you do after every lesson according to the Learner Book?",
            options = listOf(
                "Complete the self-reflection and personal goal",
                "Close the app and forget it",
                "Only speak in your native language",
                "Memorize the entire dictionary"
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 5,
            question = "What is the core speaking approach in Elevora?",
            options = listOf(
                "Speaking-first, practical, and workplace-relevant",
                "Grammar-translation test method",
                "Passive listening without speaking",
                "Silent reading only"
            ),
            correctIndex = 0
        )
    )
)

