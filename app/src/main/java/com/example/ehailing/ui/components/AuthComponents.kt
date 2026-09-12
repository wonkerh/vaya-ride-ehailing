package com.example.ehailing.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.theme.*

@Composable
fun TopBarBack(
    onBack: () -> Unit,
    title: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onBack() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Black,
            )
        }
        if (title != null) {
            Spacer(Modifier.width(8.dp))
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun CheckboxRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    onLinkClick: (() -> Unit)? = null,
    linkText: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (checked) Black else White)
                .border(1.5.dp, if (checked) Black else BorderLight, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = text,
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp,
            )
            if (onLinkClick != null && linkText != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = linkText,
                    color = TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onLinkClick() },
                )
            }
        }
    }
}

@Composable
fun AltAuthButton(
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(White)
            .border(1.dp, BorderLight, RoundedCornerShape(999.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon()
        Spacer(Modifier.width(10.dp))
        Text(
            text = label,
            color = TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun OtpInput(
    otp: List<String>,
    onOtpChange: (Int, String) -> Unit,
    length: Int = 4,
) {
    val focusRequesters = remember { List(length) { FocusRequester() } }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        repeat(length) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceLight)
                    .border(
                        width = if (otp[index].isNotEmpty()) 1.5.dp else 1.dp,
                        color = if (otp[index].isNotEmpty()) Black else BorderLight,
                        shape = RoundedCornerShape(16.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BasicTextField(
                    value = otp[index],
                    onValueChange = { newValue ->
                        val digit = newValue.filter { it.isDigit() }.take(1)
                        onOtpChange(index, digit)
                        if (digit.isNotEmpty() && index < length - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = TextPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    ),
                    cursorBrush = SolidColor(Black),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[index]),
                )
            }
        }
    }
}

@Composable
fun AvatarPicker(onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .background(SurfaceLight)
                .border(1.dp, BorderLight, CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Add photo",
                color = TextSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
            )
        }
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Black)
                .align(Alignment.BottomEnd)
                .offset(x = 4.dp, y = 4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text("+", color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PillToggle(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) Black else White)
            .border(1.dp, if (selected) Black else BorderLight, RoundedCornerShape(999.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Text(
            text = label,
            color = if (selected) White else TextPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}
@Composable
fun TosModal(onDismiss: () -> Unit) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(White)
                .padding(24.dp),
        ) {
            Text(
                text = "Terms of Service",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "These are placeholder terms for the eHailing app. In production, this will contain the full legal text. By using this app you agree to be a reasonable human being.",
                color = TextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Black)
                    .clickable { onDismiss() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Got it",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}