package com.pdm.weatherapp.model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng


data class City(
    val name: String,
    var weather: String? = null,
    var location: LatLng? = null,
    val imgUrl: String? = null,
    val bitmap: Bitmap? = null
)