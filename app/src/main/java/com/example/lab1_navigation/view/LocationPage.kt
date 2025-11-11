package com.example.lab1_navigation.view

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.lab1_navigation.viewmodel.LocationViewModel

@Composable
fun LocationPage(modifier: Modifier = Modifier, viewModel: LocationViewModel) {
    val location by viewModel.location.observeAsState()
    val status by viewModel.status.observeAsState("")

    var showMap by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Місцезнаходження",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(6.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showMap && location != null) {
                    val html = """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
                            <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
                           <style>
    html, body { height: 100%; margin: 0; background-color: #E3F2FD; }
    #map { height: 100%; width: 100%; background-color: #E3F2FD; }
</style>

                        </head>
                        <body>
                            <div id="map"></div>
                            <script>
                                var map = L.map('map').setView([${location!!.latitude}, ${location!!.longitude}], 12);
                                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                                    maxZoom: 19,
                                    attribution: '© OpenStreetMap contributors'
                                }).addTo(map);
                                L.marker([${location!!.latitude}, ${location!!.longitude}]).addTo(map);
                            </script>
                        </body>
                        </html>
                    """.trimIndent()

                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                } else {
                    Text("Карта не завантажена", color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                onClick = {
                    showMap = true
                    viewModel.fetchLocation()
                }
            ) { Text("Показати карту", color = Color.White) }

            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
                onClick = {
                    showMap = false
                    viewModel.clearLocation()
                }
            ) { Text("Приховати", color = Color.White) }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(status, fontSize = 16.sp, color = Color.DarkGray)
    }
}
