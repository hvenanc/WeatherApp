package com.pdm.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.ui.theme.HomePage
import com.pdm.weatherapp.ui.theme.ListPage
import com.pdm.weatherapp.ui.theme.MapPage

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = BottomNavItem.HomePage.route) {

        composable(route = BottomNavItem.HomePage.route) {
            HomePage()
        }

        composable(route = BottomNavItem.ListPage.route) {
            ListPage(viewModel = viewModel)
        }

        composable(route = BottomNavItem.MapPage.route) {
            MapPage()
        }

    }
}