package com.elevora.app.features.practice.vocabulary.model

/**
 * Vocabulary & Phrasal Collocation Model with phonetic pronunciation and corporate context
 */
data class WorkplaceWordItem(
    val id: String,
    val moduleNumber: Int,
    val weekNumber: Int,
    val word: String,
    val phonetic: String,
    val partOfSpeech: String,
    val definition: String,
    val executiveExample: String,
    val collocation: String,
    val isMastered: Boolean = false
)

object WorkplaceVocabularyData {
    val moduleCategories = listOf(
        "All Modules",
        "Mod 1: Foundations",
        "Mod 2: Communication",
        "Mod 3: Management",
        "Mod 4: Reporting",
        "Mod 5: Conflict",
        "Mod 6: Leadership"
    )

    val wordsList = listOf(
        WorkplaceWordItem(
            id = "w1",
            moduleNumber = 1,
            weekNumber = 1,
            word = "Responsibility",
            phonetic = "/rɪˌspɒn.sɪˈbɪl.ə.ti/",
            partOfSpeech = "noun",
            definition = "A duty or obligation to satisfactorily perform or complete a task assigned by leadership.",
            executiveExample = "My primary responsibility is overseeing the regional supply chain operations.",
            collocation = "Assume responsibility / Primary responsibility",
            isMastered = true
        ),
        WorkplaceWordItem(
            id = "w2",
            moduleNumber = 1,
            weekNumber = 1,
            word = "Oversee",
            phonetic = "/ˌəʊ.vəˈsiː/",
            partOfSpeech = "verb",
            definition = "Supervise a person, department, or project to ensure quality execution and adherence to deadlines.",
            executiveExample = "In my current role, I oversee five cross-functional project squads.",
            collocation = "Oversee delivery / Oversee operations",
            isMastered = true
        ),
        WorkplaceWordItem(
            id = "w3",
            moduleNumber = 1,
            weekNumber = 2,
            word = "Clarify",
            phonetic = "/ˈklær.ɪ.faɪ/",
            partOfSpeech = "verb",
            definition = "Make a statement or situation less confused and more comprehensible to stakeholders.",
            executiveExample = "Could you please clarify the target deadline for the preliminary beta release?",
            collocation = "Seek to clarify / Clarify expectations",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w4",
            moduleNumber = 2,
            weekNumber = 5,
            word = "Streamline",
            phonetic = "/ˈstriːm.laɪn/",
            partOfSpeech = "verb",
            definition = "Make an organization or system more efficient by employing faster, simpler working methods.",
            executiveExample = "We are migrating to cloud pipelines to streamline our weekly deployment cycles.",
            collocation = "Streamline processes / Streamline communication",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w5",
            moduleNumber = 2,
            weekNumber = 6,
            word = "Cross-Functional",
            phonetic = "/ˌkrɒsˈfʌŋk.ʃən.əl/",
            partOfSpeech = "adjective",
            definition = "Denoting an effort or team comprising members with different functional expertise.",
            executiveExample = "Effective cross-functional alignment between engineering and product is critical.",
            collocation = "Cross-functional team / Cross-functional sync",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w6",
            moduleNumber = 3,
            weekNumber = 9,
            word = "Delegate",
            phonetic = "/ˈdel.ɪ.ɡeɪt/",
            partOfSpeech = "verb",
            definition = "Entrust a task or managerial responsibility to team members with accountability.",
            executiveExample = "Leaders who delegate effectively empower their team members to grow.",
            collocation = "Delegate authority / Delegate responsibility",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w7",
            moduleNumber = 3,
            weekNumber = 10,
            word = "Consensus",
            phonetic = "/kənˈsen.səs/",
            partOfSpeech = "noun",
            definition = "A general agreement arrived at collaboratively by group members.",
            executiveExample = "After analyzing user telemetry, leadership reached a clear consensus.",
            collocation = "Reach consensus / Build consensus",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w8",
            moduleNumber = 4,
            weekNumber = 13,
            word = "Synthesize",
            phonetic = "/ˈsɪn.θə.saɪz/",
            partOfSpeech = "verb",
            definition = "Combine separate elements or data points into a coherent, executive-level summary.",
            executiveExample = "Please synthesize the quarterly sales survey into a three-bullet brief for the VP.",
            collocation = "Synthesize findings / Synthesize metrics",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w9",
            moduleNumber = 5,
            weekNumber = 17,
            word = "De-escalate",
            phonetic = "/diːˈes.kə.leɪt/",
            partOfSpeech = "verb",
            definition = "Reduce the intensity or friction of a conflict with empathetic, neutral phrasing.",
            executiveExample = "The account manager stepped in to de-escalate the client's frustration politely.",
            collocation = "De-escalate tension / De-escalate conflict",
            isMastered = false
        ),
        WorkplaceWordItem(
            id = "w10",
            moduleNumber = 6,
            weekNumber = 21,
            word = "Strategic",
            phonetic = "/strəˈtiː.dʒɪk/",
            partOfSpeech = "adjective",
            definition = "Relating to the identification of long-term overall aims and how to achieve them.",
            executiveExample = "Our strategic imperative for next fiscal year is expanding enterprise retention.",
            collocation = "Strategic initiative / Strategic vision",
            isMastered = false
        )
    )
}

