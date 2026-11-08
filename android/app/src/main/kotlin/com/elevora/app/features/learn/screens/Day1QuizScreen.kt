package com.elevora.app.features.learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.learn.model.Day1FullContent

/**
 * Screen matching Screenshot 9: Quiz – Day 1 Quiz with Radio Selection & Next/Finish
 */
@Composable
fun Day1QuizScreen(
    onBackClick: () -> Unit = {},
    onQuizComplete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val content = Day1FullContent()
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val userAnswers = remember { mutableStateMapOf<Int, Int>() }

    val currentQ = content.quizQuestions[currentQuestionIndex]
    val selectedOption = userAnswers[currentQuestionIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(ElevoraColors.SlateCard)
                    .border(1.dp, ElevoraColors.CardStroke, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ElevoraColors.TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Quiz",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Day 1 Quiz",
                    color = ElevoraColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Answer 5 quick questions to check your understanding.",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Q${currentQuestionIndex + 1} of ${content.quizQuestions.size}",
            color = ElevoraColors.GoldPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Question Card
        Text(
            text = currentQ.question,
            color = ElevoraColors.TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Options List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(currentQ.options.size) { optIdx ->
                val optionText = currentQ.options[optIdx]
                val isSelected = selectedOption == optIdx

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) ElevoraColors.SlateCard else ElevoraColors.DarkVoid)
                        .border(
                            1.dp,
                            if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { userAnswers[currentQuestionIndex] = optIdx }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Radio circle indicator
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.TextMuted,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(ElevoraColors.GoldPrimary)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = optionText,
                            color = if (isSelected) ElevoraColors.TextPrimary else ElevoraColors.TextSecondary,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next or Finish Button
        ElevoraPrimaryButton(
            text = if (currentQuestionIndex < content.quizQuestions.size - 1) "Next →" else "Finish Quiz",
            onClick = {
                if (currentQuestionIndex < content.quizQuestions.size - 1) {
                    currentQuestionIndex++
                } else {
                    onQuizComplete()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

