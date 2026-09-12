package com.example.ehailing.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.ehailing.ui.components.PillButton
import com.example.ehailing.ui.theme.*

data class RideTier(
    val id: String,
    val name: String,
    val seats: Int,
    val eta: String,
    val priceLow: Int,
    val priceHigh: Int,
    val emoji: String,
)

private val tiers = listOf(
    RideTier("standard", "Standard", 4, "3 min", 45, 60, "S"),
    RideTier("comfort",  "Comfort",  4, "5 min", 70, 90, "C"),
    RideTier("xl",       "XL",       6, "8 min", 95, 120, "X"),
)

@Composable
fun RideSelectionScreen(
    destinationName: String,
    onBack: () -> Unit,
    onConfirm: (RideTier) -> Unit,
) {
    var selected by remember { mutableStateOf(tiers[0]) }

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .systemBarsPadding(),
    ) {
        // Top: destination pill
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier.size(40.dp).clip(CircleShape).clickable { onBack() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.ArrowBack, "Back", tint = Black)
            }
            Spacer(Modifier.width(8.dp))
            Text(
                destinationName,
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Fake map area
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(MapBackground),
            contentAlignment = Alignment.Center,
        ) {
            Text("Route preview", color = TextHint, fontSize = 14.sp)
        }

        // Bottom sheet with tiers
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(White)
                .border(1.dp, BorderLight, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(20.dp),
        ) {
            Box(
                Modifier.width(40.dp).height(4.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(BorderLight)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(20.dp))

            tiers.forEach { tier ->
                val isSelected = tier.id == selected.id
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) SurfaceLight else White)
                        .border(
                            if (isSelected) 2.dp else 1.dp,
                            if (isSelected) Black else BorderLight,
                            RoundedCornerShape(16.dp),
                        )
                        .clickable { selected = tier }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        Modifier.size(44.dp).clip(CircleShape).background(White).border(1.dp, BorderLight, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(tier.emoji, color = Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(tier.name, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text("${tier.seats} seats  ·  ${tier.eta} away", color = TextSecondary, fontSize = 13.sp)
                    }
                    Text(
                        "R ${tier.priceLow}-${tier.priceHigh}",
                        color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.weight(1f))
            PillButton(
                text = "Request ${selected.name}",
                onClick = { onConfirm(selected) },
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}