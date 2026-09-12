package com.example.ehailing.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.*
import com.example.ehailing.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun OtpVerifyScreen(
    phone: String,
    onBack: () -> Unit,
    onVerified: () -> Unit,
) {
    var otp by remember { mutableStateOf(List(4) { "" }) }
    var secondsLeft by remember { mutableStateOf(30) }
    val isComplete = otp.all { it.isNotEmpty() }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) { delay(1000); secondsLeft-- }
    }
    LaunchedEffect(isComplete) {
        if (isComplete) { delay(300); onVerified() }
    }

    ScreenContainer {
        TopBarBack(onBack = onBack)
        Spacer(Modifier.height(24.dp))
        ScreenTitle("Verify your number")
        Spacer(Modifier.height(8.dp))
        GreyCaption("Enter the 4-digit code we sent to " + phone)
        Spacer(Modifier.height(40.dp))
        OtpInput(
            otp = otp,
            onOtpChange = { index, value ->
                otp = otp.toMutableList().apply { this[index] = value }
            },
            length = 4,
        )
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (secondsLeft > 0) {
                Text("Resend in ${secondsLeft}s", color = TextSecondary, fontSize = 14.sp)
            } else {
                Text(
                    "Resend code",
                    color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { secondsLeft = 30 },
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Change number",
                color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onBack() },
            )
        }
        Spacer(Modifier.height(24.dp))
        Text("For demo: any 4-digit code works", color = TextHint, fontSize = 12.sp)
    }
}