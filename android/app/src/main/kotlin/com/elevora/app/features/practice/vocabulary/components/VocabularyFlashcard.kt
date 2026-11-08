package com.elevora.app.features.practice.vocabulary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.features.practice.vocabulary.model.WorkplaceWordItem

/**
 * Luxury Vocabulary Flashcard with phonetic pronunciation,
 * audio pronunciation trigger, corporate example, and collocation pill
 */
@Composable
fun VocabularyFlashcard(
    item: WorkplaceWordItem,
    onAudioPlay: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        ElevoraColors.SlateCard,
                        Color(0xFF101C34)
                    )
                )
            )
            .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(20.dp))
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            
            // Header Row: Word, Part of Speech & Audio Pronounce Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = item.word,
                            color = ElevoraColors.TextPrimary,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(ElevoraColors.GoldPrimary.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.partOfSpeech,
                                color = ElevoraColors.GoldPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (item.isMastered) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(ElevoraColors.EmeraldCategory),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Mastered",
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = item.phonetic,
                        color = ElevoraColors.TextSecondary,
                        fontSize = 11.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                // Audio Button
                IconButton(
                    onClick = { onAudioPlay(item.word) },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF162544))
                        .border(1.dp, ElevoraColors.GoldPrimary.copy(alpha = 0.4f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
                        contentDescription = "Listen to pronunciation",
                        tint = ElevoraColors.GoldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Definition
            Text(
                text = item.definition,
                color = ElevoraColors.TextPrimary.copy(alpha = 0.88f),
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Executive Example Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF0C1424))
                    .border(1.dp, ElevoraColors.CardStroke, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "WORKPLACE CONTEXT:",
                        color = ElevoraColors.GoldLight,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.8.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "\"${item.executiveExample}\"",
                        color = ElevoraColors.TextPrimary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Collocation / Phrasing Pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Key Collocation:",
                    color = ElevoraColors.TextSecondary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF16233C))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = item.collocation,
                        color = ElevoraColors.GoldLight,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        }
    }
}

