package com.pdm.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pdm.weatherapp.ui.nav.BottomNavBar
import com.pdm.weatherapp.ui.nav.MainNavHost
import com.pdm.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel : MainViewModel by viewModels()
            WeatherAppTheme {
               Scaffold(
                   topBar = {
                       TopAppBar(
                           title = { Text(text = "Bem-Vindo") },
                           actions = {
                               IconButton(onClick = { finish() }) {
                                   Icon(
                                       imageVector = Icons.Default.ExitToApp,
                                       contentDescription = "Localizad description"
                                   )
                               }
                           }
                       )

                   },
                   bottomBar = {
                       BottomNavBar(navController = navController)
                   },
                   floatingActionButton = {
                       FloatingActionButton(onClick = {}) {
                           Icon(Icons.Default.Add, contentDescription = "Adcionar")
                       }
                   }
               ) {
                    innerPadding -> 
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController, viewModel = viewModel)
                    }
               }
            }
        }
    }
}
