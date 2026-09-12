package com.example.ehailing.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.ScreenContainer
import com.example.ehailing.ui.components.ScreenTitle
import com.example.ehailing.ui.theme.TextSecondary

@Composable
fun HomePlaceholderScreen(userName: String) {
    ScreenContainer {
        Spacer(Modifier.height(64.dp))
        ScreenTitle("Hey, $userName")
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Auth flow complete. Home screen comes in Batch B.",
            color = TextSecondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}