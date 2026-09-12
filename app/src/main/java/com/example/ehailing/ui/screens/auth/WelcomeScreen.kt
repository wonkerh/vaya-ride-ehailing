package com.example.ehailing.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.PillButton
import com.example.ehailing.ui.components.PillButtonOutline
import com.example.ehailing.ui.components.ScreenContainer
import com.example.ehailing.ui.theme.SurfaceLight
import com.example.ehailing.ui.theme.TextHint
import com.example.ehailing.ui.theme.TextPrimary
import com.example.ehailing.ui.theme.TextSecondary

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit,
    onLogIn: () -> Unit,
    onLanguageClick: () -> Unit,
) {
    ScreenContainer {
        Spacer(Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceLight)
                    .padding(horizontal = 14.dp, vertical = 6.dp),
            ) {
                Text("EN", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(Modifier.height(32.dp))
        Box(
            Modifier.fillMaxWidth().height(280.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceLight),
            contentAlignment = Alignment.Center,
        ) {
            Text("Illustration", color = TextHint, fontSize = 14.sp)
        }
        Spacer(Modifier.height(40.dp))
        Text(
            "Move around\nyour city",
            color = TextPrimary, fontSize = 40.sp, lineHeight = 44.sp,
            fontWeight = FontWeight.Bold, letterSpacing = (-0.5).sp,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            "Request a ride in seconds.\nTrack your driver live.",
            color = TextSecondary, fontSize = 16.sp, lineHeight = 22.sp,
        )
        Spacer(Modifier.weight(1f))
        PillButton(text = "Get started", onClick = onGetStarted)
        Spacer(Modifier.height(12.dp))
        PillButtonOutline(text = "Log in", onClick = onLogIn)
        Spacer(Modifier.height(24.dp))
    }
}