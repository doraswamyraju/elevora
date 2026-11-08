package com.elevora.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.theme.ElevoraTheme
import com.elevora.app.core.navigation.bottombar.BottomTab
import com.elevora.app.core.navigation.bottombar.ElevoraBottomNavBar
import com.elevora.app.core.navigation.drawer.ElevoraNavigationDrawerContent
import com.elevora.app.core.navigation.topbar.ElevoraTopBar
import com.elevora.app.features.auth.login.LoginScreen
import com.elevora.app.features.auth.register.RegisterScreen
import com.elevora.app.features.courses.CourseCatalogueScreen
import com.elevora.app.features.home.HomeScreen
import com.elevora.app.features.learn.LearningPathScreen
import com.elevora.app.features.learn.screens.Day1CompletedScreen
import com.elevora.app.features.learn.screens.Day1ControlledPracticeScreen
import com.elevora.app.features.learn.screens.Day1HubScreen
import com.elevora.app.features.learn.screens.Day1LanguageFocusScreen
import com.elevora.app.features.learn.screens.Day1LessonVideoScreen
import com.elevora.app.features.learn.screens.Day1QuizScreen
import com.elevora.app.features.learn.screens.Day1RolePlayScreen
import com.elevora.app.features.learn.screens.Day1StepType
import com.elevora.app.features.learn.screens.Day1WordsForWorkScreen
import com.elevora.app.features.learn.screens.ModuleOverviewScreen
import com.elevora.app.features.learn.screens.ModuleWeekScreen
import com.elevora.app.features.practice.speaking.SpeakingPracticeScreen
import com.elevora.app.features.profile.ProfileSettingsScreen
import com.elevora.app.features.progress.ProgressScreen
import kotlinx.coroutines.launch

enum class AppDestination {
    LOGIN,
    REGISTER,
    MAIN_TABS,
    PROFILE_SETTINGS,
    // Exact Course Curriculum Flow
    MODULE_OVERVIEW,
    MODULE_WEEK,
    DAY_HUB,
    STEP_01_VIDEO,
    STEP_02_WORDS,
    STEP_03_LANGUAGE_FOCUS,
    STEP_04_PRACTICE,
    STEP_05_ROLEPLAY,
    STEP_06_QUIZ,
    STEP_07_COMPLETED
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElevoraTheme {
                ElevoraAppRoot()
            }
        }
    }
}

@Composable
fun ElevoraAppRoot() {
    var destination by remember { mutableStateOf(AppDestination.MAIN_TABS) }
    var currentTab by remember { mutableStateOf(BottomTab.HOME) }
    var isTrainerMode by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = drawerState.isOpen || destination != AppDestination.MAIN_TABS || currentTab != BottomTab.HOME) {
        if (drawerState.isOpen) {
            coroutineScope.launch { drawerState.close() }
        } else {
            when (destination) {
                AppDestination.STEP_07_COMPLETED -> destination = AppDestination.MODULE_WEEK
                AppDestination.STEP_06_QUIZ -> destination = AppDestination.STEP_05_ROLEPLAY
                AppDestination.STEP_05_ROLEPLAY -> destination = AppDestination.STEP_04_PRACTICE
                AppDestination.STEP_04_PRACTICE -> destination = AppDestination.STEP_03_LANGUAGE_FOCUS
                AppDestination.STEP_03_LANGUAGE_FOCUS -> destination = AppDestination.STEP_02_WORDS
                AppDestination.STEP_02_WORDS -> destination = AppDestination.STEP_01_VIDEO
                AppDestination.STEP_01_VIDEO -> destination = AppDestination.DAY_HUB
                AppDestination.DAY_HUB -> destination = AppDestination.MODULE_WEEK
                AppDestination.MODULE_WEEK -> destination = AppDestination.MODULE_OVERVIEW
                AppDestination.MODULE_OVERVIEW -> {
                    destination = AppDestination.MAIN_TABS
                    currentTab = BottomTab.LEARN
                }
                AppDestination.PROFILE_SETTINGS -> destination = AppDestination.MAIN_TABS
                AppDestination.REGISTER -> destination = AppDestination.LOGIN
                AppDestination.LOGIN -> destination = AppDestination.MAIN_TABS
                AppDestination.MAIN_TABS -> {
                    if (currentTab != BottomTab.HOME) currentTab = BottomTab.HOME
                }
            }
        }
    }

    when (destination) {
        AppDestination.LOGIN -> {
            LoginScreen(
                onLoginSuccess = { isTrainer ->
                    isTrainerMode = isTrainer
                    destination = AppDestination.MAIN_TABS
                },
                onNavigateToRegister = { destination = AppDestination.REGISTER }
            )
        }
        AppDestination.REGISTER -> {
            RegisterScreen(
                onRegisterSuccess = { destination = AppDestination.MAIN_TABS },
                onNavigateToLogin = { destination = AppDestination.LOGIN }
            )
        }
        AppDestination.PROFILE_SETTINGS -> {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ElevoraColors.DeepNavy),
                topBar = {
                    ElevoraTopBar(
                        title = if (isTrainerMode) "Trainer Settings" else "Profile & Settings",
                        showBackButton = true,
                        showSidebarToggle = false,
                        showProfileIcon = false,
                        showNotificationBell = false,
                        showLogoutButton = true,
                        userName = if (isTrainerMode) "Admin Trainer" else "Priya",
                        onBackClick = { destination = AppDestination.MAIN_TABS },
                        onLogoutClick = { destination = AppDestination.LOGIN }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    ProfileSettingsScreen(
                        onBackClick = { destination = AppDestination.MAIN_TABS },
                        onLogoutClick = { destination = AppDestination.LOGIN }
                    )
                }
            }
        }

        // --- MODULE 1 CURRICULUM FLOW ---
        AppDestination.MODULE_OVERVIEW -> {
            ModuleOverviewScreen(
                onBackClick = {
                    destination = AppDestination.MAIN_TABS
                    currentTab = BottomTab.LEARN
                },
                onWeekClick = { destination = AppDestination.MODULE_WEEK }
            )
        }

        AppDestination.MODULE_WEEK -> {
            ModuleWeekScreen(
                onBackClick = { destination = AppDestination.MODULE_OVERVIEW },
                onDayClick = { dayNum ->
                    if (dayNum == 1) destination = AppDestination.DAY_HUB
                }
            )
        }

        AppDestination.DAY_HUB -> {
            Day1HubScreen(
                onBackClick = { destination = AppDestination.MODULE_WEEK },
                onStepClick = { stepType ->
                    destination = when (stepType) {
                        Day1StepType.LESSON_VIDEO -> AppDestination.STEP_01_VIDEO
                        Day1StepType.WORDS_FOR_WORK -> AppDestination.STEP_02_WORDS
                        Day1StepType.LANGUAGE_FOCUS_SAY_IT -> AppDestination.STEP_03_LANGUAGE_FOCUS
                        Day1StepType.CONTROLLED_PRACTICE -> AppDestination.STEP_04_PRACTICE
                        Day1StepType.ROLE_PLAY -> AppDestination.STEP_05_ROLEPLAY
                        Day1StepType.QUIZ -> AppDestination.STEP_06_QUIZ
                        Day1StepType.REFLECTION -> AppDestination.STEP_07_COMPLETED
                    }
                }
            )
        }

        AppDestination.STEP_01_VIDEO -> {
            Day1LessonVideoScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onNextLesson = { destination = AppDestination.STEP_02_WORDS }
            )
        }

        AppDestination.STEP_02_WORDS -> {
            Day1WordsForWorkScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onContinueClick = { destination = AppDestination.STEP_03_LANGUAGE_FOCUS }
            )
        }

        AppDestination.STEP_03_LANGUAGE_FOCUS -> {
            Day1LanguageFocusScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onContinueClick = { destination = AppDestination.STEP_04_PRACTICE }
            )
        }

        AppDestination.STEP_04_PRACTICE -> {
            Day1ControlledPracticeScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onContinueClick = { destination = AppDestination.STEP_05_ROLEPLAY }
            )
        }

        AppDestination.STEP_05_ROLEPLAY -> {
            Day1RolePlayScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onContinueClick = { destination = AppDestination.STEP_06_QUIZ }
            )
        }

        AppDestination.STEP_06_QUIZ -> {
            Day1QuizScreen(
                onBackClick = { destination = AppDestination.DAY_HUB },
                onQuizComplete = { destination = AppDestination.STEP_07_COMPLETED }
            )
        }

        AppDestination.STEP_07_COMPLETED -> {
            Day1CompletedScreen(
                onContinueToDay2 = { destination = AppDestination.MODULE_WEEK },
                onBackToWeek1 = { destination = AppDestination.MODULE_WEEK }
            )
        }

        AppDestination.MAIN_TABS -> {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ElevoraNavigationDrawerContent(
                        currentTab = currentTab,
                        onTabSelected = { tab -> currentTab = tab },
                        onProfileClick = { destination = AppDestination.PROFILE_SETTINGS },
                        onLogoutClick = { destination = AppDestination.LOGIN },
                        onCloseDrawer = { coroutineScope.launch { drawerState.close() } }
                    )
                }
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ElevoraColors.DeepNavy),
                    topBar = {
                        ElevoraTopBar(
                            showUserGreeting = currentTab == BottomTab.HOME,
                            showSidebarToggle = true,
                            showProfileIcon = true,
                            showLogoutButton = true,
                            userName = if (isTrainerMode) "Trainer Admin" else "Priya",
                            title = when (currentTab) {
                                BottomTab.HOME -> null
                                BottomTab.LEARN -> "Learning Path"
                                BottomTab.PRACTICE -> "Speaking Practice"
                                BottomTab.ASSESS -> "Assessments & Progress"
                                BottomTab.COURSES -> "Course Catalogue"
                            },
                            showBackButton = currentTab != BottomTab.HOME,
                            onBackClick = { currentTab = BottomTab.HOME },
                            onSidebarToggleClick = {
                                coroutineScope.launch {
                                    if (drawerState.isOpen) drawerState.close() else drawerState.open()
                                }
                            },
                            onProfileClick = { destination = AppDestination.PROFILE_SETTINGS },
                            onLogoutClick = { destination = AppDestination.LOGIN }
                        )
                    },
                    bottomBar = {
                        ElevoraBottomNavBar(
                            selectedTab = currentTab,
                            onTabSelected = { currentTab = it }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(ElevoraColors.DeepNavy)
                    ) {
                        when (currentTab) {
                            BottomTab.HOME -> HomeScreen(
                                onContinueLearningClick = {
                                    destination = AppDestination.MODULE_OVERVIEW
                                },
                                onSpeakingClick = { currentTab = BottomTab.PRACTICE },
                                onQuizClick = { currentTab = BottomTab.ASSESS },
                                onVocabClick = { currentTab = BottomTab.LEARN },
                                onProgressClick = { currentTab = BottomTab.ASSESS }
                            )
                            BottomTab.LEARN -> LearningPathScreen(
                                onModuleClick = { modNum ->
                                    if (modNum == 1) {
                                        destination = AppDestination.MODULE_OVERVIEW
                                    }
                                }
                            )
                            BottomTab.PRACTICE -> SpeakingPracticeScreen()
                            BottomTab.ASSESS -> ProgressScreen()
                            BottomTab.COURSES -> CourseCatalogueScreen()
                        }
                    }
                }
            }
        }
    }
}
