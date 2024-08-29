package com.pdm.weatherapp.repo

import com.google.android.gms.maps.model.LatLng
import com.pdm.weatherapp.api.WeatherService
import com.pdm.weatherapp.db.fb.FBDatabase
import com.pdm.weatherapp.model.City
import com.pdm.weatherapp.model.User

class Repository (private var listener: Listener) : FBDatabase.Listener {

    private var fbDb = FBDatabase (this)
    private var weatherService =  WeatherService()

    interface Listener {
        fun onUserLoaded(user: User)
        fun onCityAdded(city: City)
        fun onCityRemoved(city: City)
    }

    fun addCity(name: String) {
        weatherService.getLocation(name) { lat, lng ->
            fbDb.add(City(
                name = name,
                weather = "Loading..",
                location = LatLng(lat?:0.0, lng?:0.0)
            ))
        }

    }

    fun addCity(lat: Double, lng: Double) {
        weatherService.getName(lat, lng) {name ->
            fbDb.add(City(
                name = name?: "NOT_FOUND",
                location = LatLng(lat, lng)
            ))
        }
    }

    fun remove(city: City) {
        fbDb.remove(city)
    }

    fun register(userName: String, email : String) {
        fbDb.register(User(userName, email))
    }

    override fun onUserLoaded(user: User) {
        listener.onUserLoaded(user)
    }

    override fun onCityAdded(city: City) {
        listener.onCityAdded(city)
    }

    override fun onCityRemoved(city: City) {
        listener.onCityRemoved(city)
    }
}