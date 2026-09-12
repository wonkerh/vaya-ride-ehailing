package com.example.ehailing.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.data.Place
import com.example.ehailing.data.SamplePlaces
import com.example.ehailing.ui.theme.*

@Composable
fun LocationSearchScreen(
    onBack: () -> Unit,
    onSelectPlace: (Place) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val results = if (query.isBlank()) SamplePlaces.suggestions
                  else SamplePlaces.suggestions.filter {
                      it.name.contains(query, ignoreCase = true) ||
                      it.address.contains(query, ignoreCase = true)
                  }

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
    ) {
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(40.dp).clip(CircleShape).clickable { onBack() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.ArrowBack, "Back", tint = Black)
            }
        }
        Spacer(Modifier.height(12.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceLight)
                .border(1.5.dp, Black, RoundedCornerShape(999.dp))
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Default.LocationOn, null, tint = Black, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(12.dp))
            Box(Modifier.weight(1f)) {
                if (query.isEmpty()) Text("Type a destination", color = TextHint, fontSize = 16.sp)
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = TextStyle(color = TextPrimary, fontSize = 16.sp),
                    cursorBrush = SolidColor(Black),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Suggestions", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(12.dp))

        results.forEach { place ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onSelectPlace(place) }
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier.size(36.dp).clip(CircleShape).background(SurfaceLight),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.LocationOn, null, tint = Black, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(place.name, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    Text(place.address, color = TextSecondary, fontSize = 13.sp)
                }
            }
        }
    }
}