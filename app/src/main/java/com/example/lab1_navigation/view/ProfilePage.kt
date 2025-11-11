package com.example.lab1_navigation.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab1_navigation.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(modifier: Modifier = Modifier, viewModel: ProfileViewModel) {
    Column(modifier = modifier) {
        var str by remember {  mutableStateOf("Location screen! Some info will be displayed here.")}
        Text(text = str)
        Button(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth().background(Color.Magenta),
            onClick = {
                str = ""}) { Text("BTN 1") }
    }
}