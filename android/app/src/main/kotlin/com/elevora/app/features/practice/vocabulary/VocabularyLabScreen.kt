package com.elevora.app.features.practice.vocabulary

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.practice.vocabulary.components.VocabularyFlashcard
import com.elevora.app.features.practice.vocabulary.model.WorkplaceVocabularyData
import java.util.Locale

/**
 * Words for Work Vocabulary Lab with Native Text-To-Speech (TTS) Pronunciation
 * and 6-Module category filtering
 */
@Composable
fun VocabularyLabScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    
    // Native Android Text-To-Speech Engine
    var textToSpeech by remember { mutableStateOf<TextToSpeech?>(null) }
    
    DisposableEffect(Unit) {
        val tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.UK
            }
        }
        textToSpeech = tts
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    val allWords = WorkplaceVocabularyData.wordsList
    val categories = WorkplaceVocabularyData.moduleCategories

    val filteredWords = remember(selectedCategoryIndex) {
        if (selectedCategoryIndex == 0) {
            allWords
        } else {
            allWords.filter { it.moduleNumber == selectedCategoryIndex }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
    ) {
        // Top Filter Bar: Horizontal Module Categories
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories.size) { idx ->
                val isSelected = selectedCategoryIndex == idx
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.SlateCard)
                        .border(
                            1.dp,
                            if (isSelected) ElevoraColors.GoldPrimary else ElevoraColors.CardStroke,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedCategoryIndex = idx }
                        .padding(horizontal = 14.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categories[idx],
                        color = if (isSelected) ElevoraColors.DeepNavy else ElevoraColors.TextPrimary,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium
                    )
                }
            }
        }

        // Active count & info row
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text(
                text = "${filteredWords.size} Executive Words & Collocations • Tap speaker for native audio",
                color = ElevoraColors.TextSecondary,
                fontSize = 11.sp
            )
        }

        // Flashcards LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            items(filteredWords, key = { it.id }) { item ->
                VocabularyFlashcard(
                    item = item,
                    onAudioPlay = { wordToSpeak ->
                        textToSpeech?.speak(wordToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(100.dp)) // padding for bottom bar
            }
        }
    }
}
