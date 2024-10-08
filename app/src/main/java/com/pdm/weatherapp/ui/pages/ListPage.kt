package com.pdm.weatherapp.ui.pages

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import com.pdm.weatherapp.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.model.City
import com.pdm.weatherapp.repo.Repository
import com.pdm.weatherapp.ui.nav.BottomNavItem


@Composable
fun ListPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
    repo : Repository,
    navCtrl: NavHostController,
    navController: NavHostController
) {
    val activity = LocalContext.current as? Activity
    val cityList = viewModel.cities
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(cityList) { city ->
            if (city.weather == null) {
                repo.loadWeather(city)
            }
            val icon: ImageVector = if(city.isMonitored == true) {
                Icons.Outlined.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            }
            CityItem(
                city = city,
                    icon = icon,
                onClick = {
                    viewModel.city = city
                    repo.loadForecast(city)
                    navController.navigate(BottomNavItem.HomePage.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true}
                            restoreState = true
                        }
                        launchSingleTop = true
                    }
                },
                onClose = {
                    repo.remove(city)
                    Toast.makeText(context, city.name + " removida", Toast.LENGTH_LONG).show()
                }
            )

        }
    }
}

@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Favorite

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = city.weather?.imgUrl, 
            modifier = Modifier.size(75.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription ="Imagem" 
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Monitoramento",
                    modifier = Modifier.size(24.dp) // Ajuste o tamanho se necessário
                )
                Text(
                    modifier = Modifier,
                    text = city.name,
                    fontSize = 24.sp
                )
            }

            Text(
                modifier = Modifier,
                text = city.weather?.desc?:"carregando...",
                fontSize = 16.sp
            )
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Fechar")
        }
    }

}