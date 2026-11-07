package com.elevora.app.features.practice.vocabulary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.cards.ElevoraGlassCard
import com.elevora.app.core.designsystem.components.cards.ElevoraStatusBadge

data class VocabWord(
    val word: String,
    val partOfSpeech: String,
    val meaning: String,
    val workplaceExample: String
)

val sampleVocabList = listOf(
    VocabWord(
        word = "Delegate",
        partOfSpeech = "verb",
        meaning = "Entrust a task or responsibility to another person, typically someone more junior.",
        workplaceExample = "A successful manager must learn to delegate tasks effectively to team members."
    ),
    VocabWord(
        word = "Streamline",
        partOfSpeech = "verb",
        meaning = "Make an organization or system more efficient and effective by employing simpler working methods.",
        workplaceExample = "We are adopting new software to streamline the quarterly review process."
    ),
    VocabWord(
        word = "Consensus",
        partOfSpeech = "noun",
        meaning = "A general agreement among members of a group.",
        workplaceExample = "After thirty minutes of discussion, the committee reached a clear consensus."
    ),
    VocabWord(
        word = "Stakeholder",
        partOfSpeech = "noun",
        meaning = "A person or group that has an investment, share, or interest in something, as a business or industry.",
        workplaceExample = "We need to schedule a briefing with all key stakeholders before Friday."
    )
)

/**
 * Vocabulary Laboratory Screen
 */
@Composable
fun VocabularyLabScreen(
    onPlayAudio: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(sampleVocabList) { item ->
            ElevoraGlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 16.dp) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.word,
                                color = ElevoraColors.TextPrimary,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            ElevoraStatusBadge(text = item.partOfSpeech)
                        }
                        IconButton(onClick = { onPlayAudio(item.word) }) {
                            Icon(
                                imageVector = Icons.Outlined.VolumeUp,
                                contentDescription = "Listen Pronunciation",
                                tint = ElevoraColors.GoldPrimary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.meaning,
                        color = ElevoraColors.TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Example: \"${item.workplaceExample}\"",
                        color = ElevoraColors.GoldLight,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

