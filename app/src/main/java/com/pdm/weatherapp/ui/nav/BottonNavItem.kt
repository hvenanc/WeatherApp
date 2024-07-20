package com.pdm.weatherapp.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String ) {

    data object HomePage : BottomNavItem("Início", Icons.Default.Home, "home")
    data object ListPage : BottomNavItem("Lista", Icons.Default.Favorite, "list")
    data object MapPage : BottomNavItem("Mapa", Icons.Default.LocationOn, "map")
}