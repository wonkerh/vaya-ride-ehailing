package com.example.ehailing.ui.screens.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun MatchingScreen(
    onCancel: () -> Unit,
    onMatched: () -> Unit,
) {
    val transition = rememberInfiniteTransition(label = "pulse")
    val scale by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "scale",
    )

    LaunchedEffect(Unit) {
        delay(4000)
        onMatched()
    }

    Box(
        Modifier.fillMaxSize().background(White).systemBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(140.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(Black),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(White),
                )
            }
            Spacer(Modifier.height(48.dp))
            Text("Finding your ride...", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Text(
                "Connecting you with nearby drivers",
                color = TextSecondary, fontSize = 14.sp,
            )
        }

        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
                .clickable { onCancel() },
        ) {
            Text("Cancel", color = AccentRed, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}