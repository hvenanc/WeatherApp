package com.pdm.weatherapp.ui.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.repo.Repository
import com.pdm.weatherapp.ui.pages.HomePage
import com.pdm.weatherapp.ui.pages.ListPage
import com.pdm.weatherapp.ui.pages.MapPage

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    context: Context,
    repo : Repository,
) {
    NavHost(navController, startDestination = BottomNavItem.HomePage.route) {

        composable(route = BottomNavItem.HomePage.route) {
            HomePage(context = context, viewModel = viewModel, repo = repo)
        }

        composable(route = BottomNavItem.ListPage.route) {
            ListPage(context = context, viewModel = viewModel, repo = repo, navCtrl = navController, navController = navController)
        }

        composable(route = BottomNavItem.MapPage.route) {
            MapPage(context = context, viewModel = viewModel, repo = repo)
        }

    }
}