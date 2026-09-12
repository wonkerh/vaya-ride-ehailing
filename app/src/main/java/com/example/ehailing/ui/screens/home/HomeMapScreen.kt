package com.example.ehailing.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehailing.data.SamplePlaces
import com.example.ehailing.ui.theme.*

@Composable
fun HomeMapScreen(
    onSearchClick: () -> Unit,
    onRecentClick: (String) -> Unit,
    onProfileClick: () -> Unit,
) {
    Box(Modifier.fillMaxSize().background(MapBackground)) {

        // Map placeholder (replaced by GoogleMap composable later)
        Box(
            Modifier.fillMaxSize().background(MapBackground),
            contentAlignment = Alignment.Center,
        ) {
            Text("Map preview", color = TextHint, fontSize = 14.sp)
        }

        // Floating search pill at top
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(White)
                    .border(1.dp, BorderLight, RoundedCornerShape(999.dp))
                    .clickable { onSearchClick() }
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Black,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.width(12.dp))
                Text("Where to?", color = TextHint, fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Box(
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(White)
                        .border(1.dp, BorderLight, CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.MyLocation,
                        contentDescription = "My location",
                        tint = Black,
                        modifier = Modifier.size(22.dp),
                    )
                }
            }
        }

        // Bottom sheet placeholder with recents
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(White)
                .border(1.dp, BorderLight, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(20.dp),
        ) {
            // drag handle
            Box(
                Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(BorderLight)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(20.dp))
            Text("Recent", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(12.dp))

            SamplePlaces.recent.take(3).forEach { place ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onRecentClick(place.name) }
                        .padding(vertical = 10.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SurfaceLight),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("R", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(place.name, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                        Text(place.address, color = TextSecondary, fontSize = 13.sp)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}