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
import com.elevora.app.features.assess.quiz.WeeklyQuizScreen
import com.elevora.app.features.auth.login.LoginScreen
import com.elevora.app.features.auth.register.RegisterScreen
import com.elevora.app.features.courses.CourseCatalogueScreen
import com.elevora.app.features.home.HomeScreen
import com.elevora.app.features.learn.LearningPathScreen
import com.elevora.app.features.learn.lesson.LessonDetailScreen
import com.elevora.app.features.practice.speaking.SpeakingPracticeScreen
import com.elevora.app.features.profile.ProfileSettingsScreen
import com.elevora.app.features.progress.ProgressScreen
import kotlinx.coroutines.launch

enum class AppDestination {
    LOGIN,
    REGISTER,
    MAIN_TABS,
    PROFILE_SETTINGS,
    LESSON_DETAIL
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
    var selectedModuleNumber by remember { mutableStateOf(1) }
    var selectedModuleTitle by remember { mutableStateOf("Building Confidence & Core Workplace English") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Handle back button on hardware/gesture navigation
    BackHandler(enabled = drawerState.isOpen || destination != AppDestination.MAIN_TABS || currentTab != BottomTab.HOME) {
        if (drawerState.isOpen) {
            coroutineScope.launch { drawerState.close() }
        } else if (destination == AppDestination.LESSON_DETAIL) {
            destination = AppDestination.MAIN_TABS
            currentTab = BottomTab.LEARN
        } else if (destination == AppDestination.PROFILE_SETTINGS) {
            destination = AppDestination.MAIN_TABS
        } else if (destination == AppDestination.REGISTER) {
            destination = AppDestination.LOGIN
        } else if (destination == AppDestination.LOGIN) {
            destination = AppDestination.MAIN_TABS
        } else if (currentTab != BottomTab.HOME) {
            currentTab = BottomTab.HOME
        }
    }

    when (destination) {
        AppDestination.LOGIN -> {
            LoginScreen(
                onLoginSuccess = { destination = AppDestination.MAIN_TABS },
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
                        title = "Profile & Settings",
                        showBackButton = true,
                        showSidebarToggle = false,
                        showProfileIcon = false,
                        showNotificationBell = false,
                        showLogoutButton = true,
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
        AppDestination.LESSON_DETAIL -> {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ElevoraColors.DeepNavy),
                topBar = {
                    ElevoraTopBar(
                        title = "Module $selectedModuleNumber Lesson",
                        showBackButton = true,
                        showSidebarToggle = false,
                        showProfileIcon = true,
                        showNotificationBell = true,
                        showLogoutButton = true,
                        onBackClick = {
                            destination = AppDestination.MAIN_TABS
                            currentTab = BottomTab.LEARN
                        },
                        onProfileClick = { destination = AppDestination.PROFILE_SETTINGS },
                        onLogoutClick = { destination = AppDestination.LOGIN }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    LessonDetailScreen(
                        lessonTitle = "Lesson 1: $selectedModuleTitle",
                        metaInfo = "Module $selectedModuleNumber • 12 min",
                        onWordsForWorkClick = {
                            destination = AppDestination.MAIN_TABS
                            currentTab = BottomTab.PRACTICE
                        },
                        onLanguageFocusClick = {
                            destination = AppDestination.MAIN_TABS
                            currentTab = BottomTab.ASSESS
                        }
                    )
                }
            }
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
                                    selectedModuleNumber = 1
                                    selectedModuleTitle = "Building Confidence & Core Workplace English"
                                    destination = AppDestination.LESSON_DETAIL
                                },
                                onSpeakingClick = { currentTab = BottomTab.PRACTICE },
                                onQuizClick = { currentTab = BottomTab.ASSESS },
                                onVocabClick = { currentTab = BottomTab.LEARN },
                                onProgressClick = { currentTab = BottomTab.ASSESS }
                            )
                            BottomTab.LEARN -> LearningPathScreen(
                                onModuleClick = { modNum ->
                                    val titles = listOf(
                                        "Building Confidence & Core Workplace English",
                                        "Workplace Conversation & Team Communication",
                                        "Managing People, Meetings & Performance",
                                        "Emails, Reports & Presentations",
                                        "Difficult Conversations, Complaints & Problem Solving",
                                        "Leadership, Fluency & Workplace Performance"
                                    )
                                    selectedModuleNumber = modNum
                                    selectedModuleTitle = titles.getOrElse(modNum - 1) { "Managerial English" }
                                    destination = AppDestination.LESSON_DETAIL
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
