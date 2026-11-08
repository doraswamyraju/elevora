package com.elevora.app.features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elevora.app.R
import com.elevora.app.core.designsystem.colors.ElevoraColors
import com.elevora.app.core.designsystem.components.buttons.ElevoraPrimaryButton
import com.elevora.app.features.auth.components.AuthTextField

@Composable
fun LoginScreen(
    onLoginSuccess: (isTrainer: Boolean) -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var emailOrPhone by remember { mutableStateOf("user@elevora.com") }
    var password by remember { mutableStateOf("user123") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun handleSignIn() {
        val trimmedEmail = emailOrPhone.trim().lowercase()
        val trimmedPass = password.trim()

        if (trimmedEmail == "admin@elevora.com" && trimmedPass == "admin123") {
            errorMessage = null
            onLoginSuccess(true)
        } else if (trimmedEmail == "user@elevora.com" && trimmedPass == "user123") {
            errorMessage = null
            onLoginSuccess(false)
        } else {
            errorMessage = "Invalid credentials. Use user@elevora.com / user123 or admin@elevora.com / admin123"
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ElevoraColors.DeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.elevora_logo_square),
            contentDescription = "Elevora Logo",
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Welcome Back",
            color = ElevoraColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Sign in to continue your professional growth",
            color = ElevoraColors.TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = emailOrPhone,
            onValueChange = { emailOrPhone = it },
            label = "Email or Mobile Number",
            placeholder = "name@company.com or 10-digit mobile",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Enter your password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = ElevoraColors.AlertAmber,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot Password?",
            color = ElevoraColors.GoldPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.End)
                .clickable(onClick = onForgotPasswordClick)
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        ElevoraPrimaryButton(
            text = "Sign In",
            onClick = { handleSignIn() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(ElevoraColors.CardStroke)
            )
            Text(
                text = "  OR  ",
                color = ElevoraColors.TextMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(ElevoraColors.CardStroke)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        com.elevora.app.features.auth.components.GoogleSignInButton(
            onClick = { onLoginSuccess(false) },
            text = "Continue with Google"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Don't have an account? ",
                color = ElevoraColors.TextSecondary,
                fontSize = 14.sp
            )
            Text(
                text = "Sign Up",
                color = ElevoraColors.GoldPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(onClick = onNavigateToRegister)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

