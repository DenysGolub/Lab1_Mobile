package com.example.lab1_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.lab1_navigation.ui.theme.Lab1_NavigationTheme
import com.example.lab1_navigation.view.ProfilePage
import com.example.lab1_navigation.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Lab1_NavigationTheme {
                Lab1_NavigationApp()
            }
        }
    }
    @PreviewScreenSizes
    @Composable
    fun Lab1_NavigationApp() {
        var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.PROFILE) }

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                it.icon,
                                contentDescription = it.label
                            )
                        },
                        label = { Text(it.label) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }
                    )
                }
            }
        ) {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                when (currentDestination) {
                    AppDestinations.LOCATION -> LocationScreen(modifier = Modifier.padding(innerPadding))
                    AppDestinations.PROFILE -> ProfileScreen(
                        modifier = Modifier.padding(
                            innerPadding
                        )
                    )
//                AppDestinations.PROFILE -> TODO()
                }
            }

        }
    }


    @Composable
    fun LocationScreen(modifier: Modifier) {
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

    @Composable
    fun ProfileScreen(modifier: Modifier) {
        val profileViewModel = ViewModelProvider(this)[ProfileViewModel::class]
        ProfilePage(modifier, profileViewModel)
    }

}

