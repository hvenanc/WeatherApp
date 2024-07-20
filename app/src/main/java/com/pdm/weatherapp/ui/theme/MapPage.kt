package com.pdm.weatherapp.ui.theme

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdm.weatherapp.MainViewModel
import com.pdm.weatherapp.R

//@Preview(showBackground = true)
@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
) {
    val camPosState = rememberCameraPositionState()
    val recife = LatLng(-8.05, -34.9)
    val caruaru = LatLng(-8.27, -35.98)
    val joaopessoa = LatLng(-7.12, -34.84)

    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = camPosState,
        onMapLongClick = {viewModel.add("Nova Cidade", location = it)}
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