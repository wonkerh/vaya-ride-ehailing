package com.example.ehailing.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.ui.components.*
import com.example.ehailing.ui.theme.*

private val COUNTRIES = listOf(
    "+27 ZA"  to "ZA",
    "+234 NG" to "NG",
    "+254 KE" to "KE",
    "+44 UK"  to "UK",
    "+1 US"   to "US",
    "+91 IN"  to "IN",
)

@Composable
fun PhoneEntryScreen(
    onBack: () -> Unit,
    onContinue: (phone: String, countryCode: String) -> Unit,
) {
    var phone by remember { mutableStateOf("") }
    var country by remember { mutableStateOf(COUNTRIES[0]) }
    var countryPickerOpen by remember { mutableStateOf(false) }
    var tosAccepted by remember { mutableStateOf(false) }
    var showTos by remember { mutableStateOf(false) }

    val isValid = phone.length >= 9 && tosAccepted

    ScreenContainer {
        TopBarBack(onBack = onBack)
        Spacer(Modifier.height(24.dp))
        ScreenTitle("Enter your number")
        Spacer(Modifier.height(8.dp))
        GreyCaption("We will send you a code to verify it is you.")
        Spacer(Modifier.height(32.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                Modifier.height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceLight)
                    .border(1.dp, BorderLight, RoundedCornerShape(16.dp))
                    .clickable { countryPickerOpen = true }
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(country.first, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Box(Modifier.weight(1f)) {
                PillInput(
                    value = phone,
                    onValueChange = { phone = it.filter { c -> c.isDigit() }.take(11) },
                    placeholder = "Phone number",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        CheckboxRow(
            checked = tosAccepted,
            onCheckedChange = { tosAccepted = it },
            text = "I agree to the Terms of Service and Privacy Policy.",
            linkText = "Read them",
            onLinkClick = { showTos = true },
        )
        Spacer(Modifier.weight(1f))
        PillButton(
            text = "Continue",
            onClick = { onContinue(phone, country.second) },
            enabled = isValid,
        )
        Spacer(Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f).height(1.dp).background(BorderLight))
            Text("  or continue with  ", color = TextSecondary, fontSize = 13.sp)
            Box(Modifier.weight(1f).height(1.dp).background(BorderLight))
        }
        Spacer(Modifier.height(24.dp))
        AltAuthButton(
            label = "Continue with Google",
            icon = { Text("G", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextPrimary) },
            onClick = { },
        )
        Spacer(Modifier.height(10.dp))
        AltAuthButton(
            label = "Continue with Apple",
            icon = { Text("@", fontSize = 18.sp, color = TextPrimary) },
            onClick = { },
        )
        Spacer(Modifier.height(10.dp))
        AltAuthButton(
            label = "Continue with Email",
            icon = { Text("E", fontSize = 18.sp, color = TextPrimary) },
            onClick = { },
        )
        Spacer(Modifier.height(24.dp))
    }

    if (countryPickerOpen) {
        CountryPickerSheet(
            countries = COUNTRIES,
            selected = country,
            onDismiss = { countryPickerOpen = false },
            onSelect = { country = it; countryPickerOpen = false },
        )
    }
    if (showTos) {
        TosModal(onDismiss = { showTos = false })
    }
}

@Composable
private fun CountryPickerSheet(
    countries: List<Pair<String, String>>,
    selected: Pair<String, String>,
    onDismiss: () -> Unit,
    onSelect: (Pair<String, String>) -> Unit,
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(White)
                .padding(24.dp),
        ) {
            Text("Select country", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            countries.forEach { c ->
                Row(
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSelect(c) }
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(c.first, color = TextPrimary, fontSize = 16.sp, modifier = Modifier.weight(1f))
                    if (c == selected) Text("OK", color = Black, fontSize = 14.sp)
                }
            }
        }
    }
}