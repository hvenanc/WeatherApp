package com.pdm.weatherapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    val cities : List<City>
        get() = _cities

    fun remove(city: City) {
        _cities.remove(city)
    }

    fun add(city: String, location: LatLng? = null) {
        _cities.add(City(city, "Carregando Clima...", location))
    }
}

data class City(
    val name : String,
    var weather : String,
    var location : LatLng? = null,
)

private fun getCities() = List(30) {i ->
    City(name = "Cidade $i", weather = "Carregando o Clima...")
}