package com.example.lab1_navigation.view
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
import com.example.lab1_navigation.viewmodel.SettingsViewModel

@Composable
fun SettingsPage(modifier: Modifier = Modifier, viewModel: SettingsViewModel) {
    val status by viewModel.status.observeAsState("Налаштування ще не змінювались")
    val isDarkMode by viewModel.isDarkMode.observeAsState(false)
    val language by viewModel.language.observeAsState("Українська")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Налаштування",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkMode) Color.White else Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(6.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = if (isDarkMode) Color(0xFF424242) else Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Тема: ${if (isDarkMode) "Темна" else "Світла"}",
                    color = if (isDarkMode) Color.White else Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    "Мова: $language",
                    color = if (isDarkMode) Color.White else Color.Black,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.toggleTheme() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Змінити тему", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.changeLanguage() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Змінити мову", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.resetSettings() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Скинути", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = status,
            fontSize = 16.sp,
            color = if (isDarkMode) Color.LightGray else Color.DarkGray
        )
    }
}
