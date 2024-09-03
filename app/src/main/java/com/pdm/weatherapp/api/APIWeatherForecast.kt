package com.pdm.weatherapp.api

data class APIWeatherForecast(
    var location: APILocation? = null,
    var current: APIWeatherForecast? = null,
    var forecast: APIForecast? = null
)

data class APIForecast (
    var forecastday: List<APIForecastDay>? = null
)

data class APIForecastDay (
    var date: String? = null,
    var day : APIWeather? = null
)