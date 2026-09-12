package com.example.ehailing.ui.screens.ride

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun TrackingScreen(
    driverName: String = "Thabo M.",
    carModel: String = "Toyota Corolla",
    plate: String = "CA 123-456",
    onComplete: () -> Unit,
) {
    var etaSeconds by remember { mutableStateOf(180) }
    val etaMin = (etaSeconds / 60).coerceAtLeast(0)

    LaunchedEffect(Unit) {
        while (etaSeconds > 0) {
            delay(1000)
            etaSeconds--
        }
    }

    Box(Modifier.fillMaxSize().background(MapBackground).systemBarsPadding()) {

        // Map placeholder with fake route
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Map preview", color = TextHint, fontSize = 14.sp)
                Spacer(Modifier.height(8.dp))
                Box(
                    Modifier
                        .width(180.dp)
                        .height(3.dp)
                        .background(Black)
                        .clip(RoundedCornerShape(999.dp)),
                )
                Spacer(Modifier.height(8.dp))
                Text("●  ────  🚗  ────  ●", color = TextPrimary, fontSize = 18.sp)
            }
        }

        // Top pill — ETA
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(Black)
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Text(
                "$etaMin min away",
                color = White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
            )
        }

        // Bottom card
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

            Text(
                "Arriving in $etaMin min",
                color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold,
                letterSpacing = (-0.3).sp,
            )
            Spacer(Modifier.height(4.dp))
            Text("$driverName is on the way", color = TextSecondary, fontSize = 14.sp)

            Spacer(Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(48.dp).clip(CircleShape).background(SurfaceLight).border(1.dp, BorderLight, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("TM", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(driverName, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Text("$carModel  ·  $plate", color = TextSecondary, fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActionButton(
                    icon = { Icon(Icons.Default.Message, "Message", tint = Black, modifier = Modifier.size(18.dp)) },
                    label = "Message",
                    filled = false,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                )
                ActionButton(
                    icon = { Icon(Icons.Default.Call, "Call", tint = White, modifier = Modifier.size(18.dp)) },
                    label = "Call",
                    filled = true,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(Modifier.height(12.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceLight)
                    .clickable { onComplete() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "Simulate arrival",
                    color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    icon: @Composable () -> Unit,
    label: String,
    filled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .height(52.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(if (filled) Black else White)
            .border(1.dp, if (filled) Black else BorderLight, RoundedCornerShape(999.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon()
        Spacer(Modifier.width(8.dp))
        Text(
            label,
            color = if (filled) White else TextPrimary,
            fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
        )
    }
}