package com.pdm.weatherapp.ui.theme

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
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.weatherapp.City
import com.pdm.weatherapp.MainViewModel


@Composable
fun ListPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
) {
    val cityList = viewModel.cities
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(cityList) { city ->
            CityItem(city = city,
                onClick = {
                    Toast.makeText(context, city.name, Toast.LENGTH_LONG).show()
                },
                onClose = {
                    viewModel.remove(city)
                    Toast.makeText(context, city.name + " removida", Toast.LENGTH_LONG).show()
                }
            )

        }
    }
}

@Composable
fun CityItem(
    city : City,
    onClick : () -> Unit,
    onClose : () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.FavoriteBorder,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier,
                text = city.name,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier,
                text = city.weather,
                fontSize = 16.sp
            )
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Fechar")
        }
    }

}