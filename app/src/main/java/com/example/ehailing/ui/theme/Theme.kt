package com.example.ehailing.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EHailingColorScheme = lightColorScheme(
    primary          = Black,
    onPrimary        = White,
    secondary        = Black,
    onSecondary      = White,
    background       = White,
    onBackground     = Black,
    surface          = White,
    onSurface        = Black,
    surfaceVariant   = SurfaceLight,
    onSurfaceVariant = TextSecondary,
    outline          = BorderLight,
    error            = AccentRed,
    onError          = White,
)

@Composable
fun EHailingTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = EHailingColorScheme,
        typography  = EHailingTypography,
        content     = content,
    )
}