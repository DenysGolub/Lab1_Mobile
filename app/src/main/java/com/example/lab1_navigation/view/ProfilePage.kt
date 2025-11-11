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
import com.example.lab1_navigation.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(modifier: Modifier = Modifier, viewModel: ProfileViewModel) {
    val account by viewModel.account.observeAsState()
    var statusText by remember { mutableStateOf("Профіль ще не завантажено") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Профіль користувача",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5)
        )

        Spacer(modifier = Modifier.height(24.dp))

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
                    .padding(20.dp)
            ) {
                if (account == null) {
                    Text(
                        text = "Дані акаунта ще не завантажені.",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                } else {
                    Text("Ім’я: ${account!!.name}", fontSize = 18.sp)
                    Text("Логін: ${account!!.username}", fontSize = 18.sp)
                    Text("Job: ${account!!.job}", fontSize = 18.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
            onClick = {
                viewModel.getAccount()
                statusText = "Інформація оновлюється..."
            }
        ) {
            Text("Отримати дані", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688)),
            onClick = {
                statusText = if (account == null)
                    "Спочатку завантаж дані!"
                else
                    "Останнє оновлення: ${account!!.name}"
            }
        ) {
            Text("Показати статус", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = statusText, fontSize = 16.sp, color = Color.DarkGray)
    }
}
