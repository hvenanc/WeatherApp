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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.db.fb.FBDatabase
import com.pdm.weatherapp.model.City

//@Preview(showBackground = true)
@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
    fbDB : FBDatabase
) {
    val camPosState = rememberCameraPositionState()
    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }
    val recife = LatLng(-8.05, -34.9)
    val caruaru = LatLng(-8.27, -35.98)
    val joaopessoa = LatLng(-7.12, -34.84)

    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = camPosState,
        onMapLongClick = {fbDB.add(city = City("City "+it.latitude, weather = "", location = it))},
        properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
        uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {

        viewModel.cities.forEach {
            if(it.location != null) {
                Marker(
                    state = MarkerState(position = it.location!!),
                    title = it.name,
                    snippet = "${it.location}"
                )
            }
        }

        Marker(
            state = MarkerState(position = recife),
            title = "Recife",
            snippet = "Marcador de Recife",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_BLUE
            )
        )

        Marker(
            state = MarkerState(position = caruaru),
            title = "Caruaru",
            snippet = "Marcador de Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_GREEN
            )
        )

        Marker(
            state = MarkerState(position = joaopessoa),
            title = "João Pessoa",
            snippet = "Marcador de João Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_YELLOW)
        )
    }
}