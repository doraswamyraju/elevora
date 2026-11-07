package com.elevora.app.core.navigation.topbar.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors

/**
 * TopBar User Greeting Atom: Avatar + "Hi, Priya 👋" + subtitle
 */
@Composable
fun TopBarUserGreeting(
    userName: String = "Priya",
    subtitle: String = "Continue your learning journey",
    onProfileClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.elevora_logo_square),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .border(1.5.dp, ElevoraColors.GoldPrimary, CircleShape)
                .clickable(onClick = onProfileClick)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.clickable(onClick = onProfileClick)
        ) {
            Text(
                text = "Hi, $userName \uD83D\uDC4B",
                color = ElevoraColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                text = subtitle,
                color = ElevoraColors.TextSecondary,
                fontSize = 12.sp
            )
        }
    }
}

