package com.example.ehailing.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.*
import com.example.ehailing.ui.theme.*

@Composable
fun ProfileSetupScreen(
    phone: String,
    onBack: () -> Unit,
    onDone: (name: String, email: String, gender: String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val isValid = name.trim().length >= 2 &&
        email.contains("@") && email.contains(".") &&
        gender.isNotEmpty()

    ScreenContainer {
        TopBarBack(onBack = onBack)
        Spacer(Modifier.height(24.dp))

        ScreenTitle("Set up your profile")
        Spacer(Modifier.height(8.dp))
        GreyCaption("This is how drivers will know you.")

        Spacer(Modifier.height(32.dp))

        AvatarPicker(onClick = { /* stub: open gallery */ })

        Spacer(Modifier.height(32.dp))

        PillInput(
            value = name,
            onValueChange = { name = it },
            placeholder = "Full name",
        )
        Spacer(Modifier.height(12.dp))
        PillInput(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Gender (optional, helps with safety matching)",
            color = TextSecondary,
            fontSize = 13.sp,
        )
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PillToggle("Male", gender == "male") { gender = "male" }
            PillToggle("Female", gender == "female") { gender = "female" }
            PillToggle("Prefer not to say", gender == "na") { gender = "na" }
        }

        Spacer(Modifier.weight(1f))

        PillButton(
            text = "Create account",
            onClick = { onDone(name.trim(), email.trim(), gender) },
            enabled = isValid,
        )
        Spacer(Modifier.height(24.dp))
    }
}