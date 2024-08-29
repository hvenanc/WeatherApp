package com.pdm.weatherapp.model

import android.graphics.Bitmap

data class Weather(
    val date: String,
    val desc: String,
    val temp: Double,
    val imgUrl : String,
    var bitmap : Bitmap? = null
)