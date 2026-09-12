package com.example.ehailing.ui.screens.driver

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun DriverHomeScreen(
    onAcceptRide: () -> Unit,
) {
    var online by remember { mutableStateOf(false) }
    var incomingRequest by remember { mutableStateOf(false) }

    // Simulate an incoming request 6 seconds after going online
    LaunchedEffect(online) {
        incomingRequest = false
        if (online) {
            delay(6000)
            incomingRequest = true
        }
    }

    Box(Modifier.fillMaxSize().background(MapBackground).systemBarsPadding()) {

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Map preview", color = TextHint, fontSize = 14.sp)
        }

        // Top: online toggle pill
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(if (online) Black else White)
                .border(1.dp, if (online) Black else BorderLight, RoundedCornerShape(999.dp))
                .clickable { online = !online }
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PulsingDot(active = online)
                Spacer(Modifier.width(10.dp))
                Text(
                    if (online) "You are online" else "You are offline",
                    color = if (online) White else TextPrimary,
                    fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                )
            }
        }

        // Bottom sheet
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(White)
                .border(1.dp, BorderLight, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(24.dp),
        ) {
            Box(
                Modifier.width(40.dp).height(4.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(BorderLight)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(20.dp))

            when {
                incomingRequest -> RideRequestCard(
                    onAccept = {
                        incomingRequest = false
                        onAcceptRide()
                    },
                    onDecline = { incomingRequest = false },
                )
                online -> LookingForRidesCard()
                else -> OfflineEarningsCard()
            }
        }
    }
}

@Composable
private fun OfflineEarningsCard() {
    Column {
        Text("Today's earnings", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        Text("R 0.00", color = TextPrimary, fontSize = 40.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.5).sp)
        Spacer(Modifier.height(4.dp))
        Text("0 rides completed", color = TextSecondary, fontSize = 14.sp)
        Spacer(Modifier.height(20.dp))
        Text(
            "Go online to start earning",
            color = TextSecondary, fontSize = 14.sp,
        )
    }
}

@Composable
private fun LookingForRidesCard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(8.dp))
        PulsingDot(active = true, size = 16.dp)
        Spacer(Modifier.height(20.dp))
        Text("Looking for rides...", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        Text("Stay in a busy area to get more requests", color = TextSecondary, fontSize = 14.sp)
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun RideRequestCard(
    onAccept: () -> Unit,
    onDecline: () -> Unit,
) {
    Column {
        Text("New ride request", color = TextSecondary, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))
        Text("Thabo M.", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text("Pickup 1.2 km away  ·  3 min", color = TextSecondary, fontSize = 14.sp)
        Spacer(Modifier.height(16.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceLight)
                .padding(14.dp),
        ) {
            Text("Sandton City", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(6.dp))
            Text("↓", color = TextHint, fontSize = 14.sp)
            Spacer(Modifier.height(6.dp))
            Text("O.R. Tambo Airport", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Fare", color = TextSecondary, fontSize = 14.sp)
            Spacer(Modifier.weight(1f))
            Text("R 145.00", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(White)
                    .border(1.dp, BorderLight, RoundedCornerShape(999.dp))
                    .clickable { onDecline() },
                contentAlignment = Alignment.Center,
            ) {
                Text("Decline", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Box(
                Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Black)
                    .clickable { onAccept() },
                contentAlignment = Alignment.Center,
            ) {
                Text("Accept", color = White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun PulsingDot(active: Boolean, size: androidx.compose.ui.unit.Dp = 10.dp) {
    val transition = rememberInfiniteTransition(label = "dot")
    val scale by transition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "scale",
    )
    Box(
        Modifier
            .size(size)
            .scale(if (active) scale else 1f)
            .clip(CircleShape)
            .background(if (active) White else TextHint),
    )
}