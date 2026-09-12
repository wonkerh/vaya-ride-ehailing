package com.example.ehailing.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.*

// ---------- PillButton (black, filled) ----------

@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
) {
    val bg = if (enabled) Black else BorderLight
    val fg = if (enabled) White else TextHint
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .clickable(enabled = enabled && !loading) { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = fg,
                strokeWidth = 2.dp,
                modifier = Modifier.size(22.dp),
            )
        } else {
            Text(
                text = text,
                color = fg,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

// ---------- PillButtonOutline (white, black border) ----------

@Composable
fun PillButtonOutline(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Black,
    borderColor: Color = Black,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(White)
            .border(1.dp, borderColor, RoundedCornerShape(999.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

// ---------- PillInput (grey pill, black focus border) ----------

@Composable
fun PillInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
) {
    var focused by remember { mutableStateOf(false) }
    val borderColor = if (focused) Black else BorderLight
    val borderWidth = if (focused) 1.5.dp else 1.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceLight)
            .border(borderWidth, borderColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            leading()
            Spacer(Modifier.width(12.dp))
        }
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = TextHint,
                    fontSize = 16.sp,
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = TextStyle(
                    color = TextPrimary,
                    fontSize = 16.sp,
                ),
                cursorBrush = SolidColor(Black),
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focused = it.isFocused },
            )
        }
    }
}



// ---------- FlatCard (white, 1px border, rounded) ----------

@Composable
fun FlatCard(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(White)
            .border(1.dp, BorderLight, RoundedCornerShape(cornerRadius.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(16.dp),
        content = content,
    )
}

// ---------- GreyCard (surface light, 1px border) ----------

@Composable
fun GreyCard(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val borderColor = if (selected) Black else BorderLight
    val borderWidth = if (selected) 2.dp else 1.dp
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(SurfaceLight)
            .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(16.dp),
        content = content,
    )
}

// ---------- Text helpers ----------

@Composable
fun BoldHeadline(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = TextPrimary,
        fontSize = 40.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.5).sp,
        modifier = modifier,
    )
}

@Composable
fun ScreenTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = TextPrimary,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.3).sp,
        modifier = modifier,
    )
}

@Composable
fun GreyCaption(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = TextSecondary,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        modifier = modifier,
    )
}

// ---------- Screen container ----------

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
        content = content,
    )
}