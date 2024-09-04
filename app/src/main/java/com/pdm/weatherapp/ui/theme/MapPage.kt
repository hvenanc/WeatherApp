package com.pdm.weatherapp.ui.theme

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.repo.Repository

//@Preview(showBackground = true)
@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
    repo : Repository
) {
    val camPosState = rememberCameraPositionState()
    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = camPosState,
        onMapLongClick = {
                         repo.addCity(lat = it.latitude, lng = it.longitude)
        },
        properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
        uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {

        viewModel.cities.forEach {city ->
            if(city.location != null) {
                var marker = BitmapDescriptorFactory.defaultMarker()

                if(city.weather == null) {
                    repo.loadWeather(city)
                } else if(city.weather!!.bitmap == null) {
                    repo.loadBitmap(city)
                } else {
                    marker = BitmapDescriptorFactory
                        .fromBitmap(city.weather!!.bitmap!!.scale(200, 200))
                }

                Marker(
                    state = MarkerState(position = city.location!!),
                    icon = marker,
                    title = city.name,
                    snippet = city.weather?.desc?:"Carregando..."
                )
            }
        }
    }
}