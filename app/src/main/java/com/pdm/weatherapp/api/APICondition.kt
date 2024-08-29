package com.pdm.weatherapp.api

data class APICondition (
    var text : String? = null,
    var icon : String? = null
)

data class APIWeather (
    var last_updated: String? = null,
    var temp_c: Double? = 0.0,
    var maxtemp_c: Double? = 0.0,
    var mintemp_c: Double? = 0.0,
    var condition: APICondition? = null
)

data class APICurrentWeather (
    var APILocation : APILocation? = null,
    var current : APIWeather? = null
)