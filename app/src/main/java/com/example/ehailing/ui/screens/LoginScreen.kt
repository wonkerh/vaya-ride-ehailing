package com.example.ehailing.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.BoldHeadline
import com.example.ehailing.ui.components.PillButton
import com.example.ehailing.ui.components.PillInput
import com.example.ehailing.ui.components.ScreenContainer
import com.example.ehailing.ui.theme.Black
import com.example.ehailing.ui.theme.TextPrimary
import com.example.ehailing.ui.theme.TextSecondary

@Composable
fun LoginScreen(
    onContinue: (String) -> Unit,
    onSignUp: () -> Unit,
) {
    var phone by remember { mutableStateOf("") }
    val isValid = phone.length >= 9

    ScreenContainer {
        Spacer(Modifier.height(64.dp))
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Black)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "eHailing",
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(64.dp))
        BoldHeadline("Get moving")
        Spacer(Modifier.height(32.dp))
        PillInput(
            value = phone,
            onValueChange = { phone = it.filter { c -> c.isDigit() } },
            placeholder = "Phone number",
            leading = {
                Text(
                    text = "+27",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
        Spacer(Modifier.weight(1f))
        PillButton(
            text = "Continue",
            onClick = { onContinue(phone) },
            enabled = isValid,
        )
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "New here?  ", color = TextSecondary, fontSize = 14.sp)
            Text(
                text = "Sign up",
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onSignUp() },
            )
        }
    }
}