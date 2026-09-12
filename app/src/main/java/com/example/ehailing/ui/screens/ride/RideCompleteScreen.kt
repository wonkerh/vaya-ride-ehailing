package com.example.ehailing.ui.screens.ride

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.*

@Composable
fun RideCompleteScreen(
    fareBase: Int = 25,
    fareDistance: Int = 98,
    fareTime: Int = 22,
    driverName: String = "Thabo M.",
    onDone: () -> Unit,
) {
    var stars by remember { mutableStateOf(0) }
    var tipPercent by remember { mutableStateOf<Int?>(null) }

    val total = fareBase + fareDistance + fareTime
    val tip = when (tipPercent) {
        null -> 0
        else -> total * tipPercent!! / 100
    }

    Box(Modifier.fillMaxSize().background(MapBackground).systemBarsPadding()) {

        // Dimmed map backdrop
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Map", color = TextHint, fontSize = 14.sp)
        }

        // Receipt sheet
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

            Text("Ride complete", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.3).sp)
            Spacer(Modifier.height(4.dp))
            Text("Thanks for riding with us", color = TextSecondary, fontSize = 14.sp)

            Spacer(Modifier.height(24.dp))
            Divider()
            Spacer(Modifier.height(12.dp))

            LineItem("Base fare", fareBase)
            LineItem("Distance", fareDistance)
            LineItem("Time", fareTime)
            if (tip > 0) LineItem("Tip ($tipPercent%)", tip)

            Spacer(Modifier.height(12.dp))
            Divider()
            Spacer(Modifier.height(12.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Total", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.weight(1f))
                Text("R ${total + tip}.00", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(24.dp))

            Text("Rate $driverName", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                (1..5).forEach { i ->
                    Text(
                        text = if (i <= stars) "★" else "☆",
                        color = if (i <= stars) Black else TextHint,
                        fontSize = 36.sp,
                        modifier = Modifier.clickable { stars = i },
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Text("Add a tip", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(10, 15, 20).forEach { pct ->
                    TipPill(
                        label = "$pct%",
                        selected = tipPercent == pct,
                        onClick = { tipPercent = if (tipPercent == pct) null else pct },
                    )
                }
                TipPill(
                    label = "No tip",
                    selected = tipPercent == null,
                    onClick = { tipPercent = null },
                )
            }

            Spacer(Modifier.height(24.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Black)
                    .clickable { onDone() },
                contentAlignment = Alignment.Center,
            ) {
                Text("Done", color = White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun LineItem(label: String, amount: Int) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(label, color = TextSecondary, fontSize = 14.sp)
        Spacer(Modifier.weight(1f))
        Text("R $amount.00", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun Divider() {
    Box(Modifier.fillMaxWidth().height(1.dp).background(BorderLight))
}

@Composable
private fun TipPill(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) Black else White)
            .border(1.dp, if (selected) Black else BorderLight, RoundedCornerShape(999.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            label,
            color = if (selected) White else TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}