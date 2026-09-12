package com.example.ehailing.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.PillButton
import com.example.ehailing.ui.components.ScreenContainer
import com.example.ehailing.ui.theme.TextPrimary
import com.example.ehailing.ui.theme.TextSecondary

@Composable
private fun PlaceholderScreen(
    title: String,
    onNext: (() -> Unit)? = null,
    nextLabel: String = "Next",
) {
    ScreenContainer {
        Spacer(Modifier.height(64.dp))
        Text(
            text = title,
            color = TextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Coming in the next batch",
            color = TextSecondary,
            fontSize = 14.sp,
        )
        if (onNext != null) {
            Spacer(Modifier.weight(1f))
            PillButton(text = nextLabel, onClick = onNext)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun RiderHomeScreen(onRequestRide: () -> Unit) =
    PlaceholderScreen("Rider Home", onRequestRide, "Request a Ride")

@Composable
fun DriverHomeScreen(onAcceptRide: () -> Unit) =
    PlaceholderScreen("Driver Home", onAcceptRide, "Accept Ride")

@Composable
fun MatchingScreen(onCancel: () -> Unit, onMatched: () -> Unit) =
    PlaceholderScreen("Finding your ride...", onMatched, "Simulate Match")

@Composable
fun TrackingScreen(onComplete: () -> Unit) =
    PlaceholderScreen("Tracking", onComplete, "Complete Ride")

@Composable
fun RideCompleteScreen(onDone: () -> Unit) =
    PlaceholderScreen("Ride complete", onDone, "Done")