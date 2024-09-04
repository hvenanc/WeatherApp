package com.pdm.weatherapp.model

data class Forecast(
    val date: String,
    val weather: String,
    val tempMin: Double,
    val tempMax: Double,
    val imgUrl: String,
)