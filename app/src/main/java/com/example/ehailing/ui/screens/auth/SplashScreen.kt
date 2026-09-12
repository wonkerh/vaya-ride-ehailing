package com.example.ehailing.ui.screens.auth

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.Black
import com.example.ehailing.ui.theme.TextPrimary
import com.example.ehailing.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinish: (loggedIn: Boolean) -> Unit) {
    val scale = remember { Animatable(0.6f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            ),
        )
    }

    LaunchedEffect(Unit) {
        // Stub: check login state via callback, then advance
        delay(2000)
        onFinish(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .scale(scale.value)
                    .clip(CircleShape)
                    .background(Black),
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = "eHailing",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Get moving",
                color = TextSecondary,
                fontSize = 14.sp,
            )
        }
    }
}