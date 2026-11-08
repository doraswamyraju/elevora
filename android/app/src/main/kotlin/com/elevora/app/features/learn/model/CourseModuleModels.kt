package com.elevora.app.features.learn.model

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

data class TargetWord(
    val word: String,
    val definition: String,
    val example: String
)

data class Day1Curriculum(
    val moduleNumber: Int = 1,
    val moduleTitle: String = "Building Confidence & Core Workplace English",
    val weekNumber: Int = 1,
    val classNumber: Int = 1,
    val lessonTitle: String = "Professional Introductions",
    val videoDuration: String = "05:24",
    val objectives: List<String> = listOf(
        "Use English confidently for professional introductions.",
        "Apply the target language in a realistic managerial situation.",
        "Speak clearly for 2–5 minutes with reduced hesitation."
    ),
    val wordsForWork: List<TargetWord> = listOf(
        TargetWord("role", "The position or purpose that someone has in an organization", "My role involves coordinating cross-functional deliveries."),
        TargetWord("department", "A division of a company dealing with a specific functional area", "I lead the Operations department at our regional hub."),
        TargetWord("experience", "Practical knowledge or skill gained from working in a field over time", "I have over eight years of experience in supply chain."),
        TargetWord("responsibility", "A duty or obligation to deal with something or oversee people", "My main responsibility is ensuring on-time project execution."),
        TargetWord("position", "A formal job post or rank within an organizational hierarchy", "She accepted the Senior Product Lead position."),
        TargetWord("team", "A group of professionals collaborating to achieve common targets", "Our team consists of twelve skilled software engineers."),
        TargetWord("background", "A person's professional history, education, and past track record", "My background is primarily in industrial engineering."),
        TargetWord("objective", "A specific measurable goal or milestone you want to reach", "Our primary objective this quarter is increasing retention.")
    ),
    val languageFocusRules: List<String> = listOf(
        "Use short, clear sentences instead of complicated clauses.",
        "Choose the correct tense (Present Simple for daily routines and duties).",
        "Ask follow-up questions to keep managerial conversations active.",
        "Avoid translating word-for-word from your native language."
    ),
    val sayItPhrases: List<String> = listOf(
        "Let me explain that clearly.",
        "Could you please clarify that?",
        "My main responsibility is…",
        "The priority is…",
        "Let’s look at the best solution.",
        "I’d like to make sure we agree on the next step."
    ),
    val fillInBlankExercises: List<FillInBlankQuestion> = listOf(
        FillInBlankQuestion(1, "My main ", " is leading the regional sales department.", listOf("responsibility", "department", "background"), "responsibility"),
        FillInBlankQuestion(2, "Could you please ", " that project deadline?", listOf("clarify", "look", "position"), "clarify"),
        FillInBlankQuestion(3, "Our primary ", " this quarter is client retention.", listOf("objective", "team", "role"), "objective"),
        FillInBlankQuestion(4, "My professional ", " is in civil and mechanical engineering.", listOf("background", "sentence", "repeat"), "background"),
        FillInBlankQuestion(5, "Let me ", " that clearly for the leadership team.", listOf("explain", "manage", "hesitate"), "explain")
    ),
    val day1QuizList: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            question = "Which sentence uses the target word 'responsibility' correctly?",
            options = listOf(
                "My main responsibility is overseeing cross-functional projects.",
                "I have responsibility my breakfast every morning.",
                "The responsibility car was delayed on the highway.",
                "He responsibled to the vice president yesterday."
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 2,
            question = "When you did not understand a colleague's explanation, what is the best phrase to use?",
            options = listOf(
                "Could you please clarify that?",
                "What are you saying right now?",
                "Stop talking and change the topic.",
                "I do not want to listen."
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 3,
            question = "What is the recommended core rule from the Language Focus section?",
            options = listOf(
                "Use short, clear sentences and avoid word-for-word translation.",
                "Speak as fast as possible without pauses.",
                "Always translate every single word from your native language.",
                "Only speak in single words."
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 4,
            question = "In the Managerial Introduction role-play, the manager must:",
            options = listOf(
                "Explain the situation, ask at least 3 questions, and agree on a next step.",
                "Give a 60-minute uninterrupted speech.",
                "Ask no questions and leave immediately.",
                "Only read bullet points silently."
            ),
            correctIndex = 0
        ),
        QuizQuestion(
            id = 5,
            question = "Which phrase helps align both parties at the end of a managerial discussion?",
            options = listOf(
                "I’d like to make sure we agree on the next step.",
                "Let us cancel all future meetings.",
                "I will forget what we discussed.",
                "None of the above."
            ),
            correctIndex = 0
        )
    ),
    val rolePlayPrompt: String = "Manager & Colleague Scenario: Introduce yourself, explain your department & main responsibility, ask 3 follow-up questions, and agree on the next step."
)
