package com.example.ehailing.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Using the platform default font for now.
// Swap FontFamily.Default -> Inter once the Inter .ttf files are in res/font/.
private val AppFont = FontFamily.Default

val EHailingTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.5).sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp,
    ),
)