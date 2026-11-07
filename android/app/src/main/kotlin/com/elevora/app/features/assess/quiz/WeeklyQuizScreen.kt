package com.elevora.app.features.assess.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.assess.quiz.components.QuizOptionCard

/**
 * Weekly Quiz Assessment Screen
 */
@Composable
fun WeeklyQuizScreen(
    weekNumber: Int = 1,
    questionNumber: Int = 3,
    totalQuestions: Int = 10,
    onNextQuestion: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedOptionIndex by remember { mutableIntStateOf(0) }

    val options = listOf(
        "attend",
        "attends",
        "attending",
        "attended"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Question $questionNumber of $totalQuestions",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { questionNumber / totalQuestions.toFloat() },
            modifier = Modifier.fillMaxWidth().height(6.dp),
            color = ElevoraColors.GoldPrimary,
            trackColor = ElevoraColors.CardStroke,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Choose the correct form of the verb.",
            color = ElevoraColors.GoldLight,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "I ________ the team meeting every Monday.",
            color = ElevoraColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            val letters = listOf("A", "B", "C", "D")
            options.forEachIndexed { index, opt ->
                QuizOptionCard(
                    letter = letters[index],
                    optionText = opt,
                    isSelected = selectedOptionIndex == index,
                    onSelect = { selectedOptionIndex = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        ElevoraPrimaryButton(
            text = "Next",
            onClick = onNextQuestion
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

