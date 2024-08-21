package com.pdm.weatherapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.pdm.weatherapp.model.City
import com.pdm.weatherapp.model.User

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    private val _user = mutableStateOf(User("Henrique", ""))
    val user : User
        get() = _user.value
    val cities : List<City>
        get() = _cities

    fun remove(city: City) {
        _cities.remove(city)
    }

    fun add(city: String, location: LatLng? = null) {
        _cities.add(City(city, "Carregando Clima...", location = null))
    }
}

private fun getCities() = List(30) {i ->
    City(name = "Cidade $i", weather = "Carregando o Clima...")
}