package com.pdm.weatherapp

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pdm.weatherapp.db.fb.FBDatabase
import com.pdm.weatherapp.model.City
import com.pdm.weatherapp.model.User
import com.pdm.weatherapp.repo.Repository

class MainViewModel : ViewModel(), Repository.Listener {
    private val _cities = mutableStateMapOf<String, City>()
    private val _user = mutableStateOf(User("Henrique", ""))
    private var _city = mutableStateOf<City?>(null)
    val user : User
        get() = _user.value
    val cities : List<City>
        get() = _cities.values.toList()

    var city: City?
        get() = _city.value
        set(tmp) {
            _city.value = tmp?.copy()
        }

    private var _loggedIn = mutableStateOf(false)
    val loggedIn: Boolean
        get() = _loggedIn.value

    private val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _loggedIn.value = firebaseAuth.currentUser != null
    }

    init {
        listener.onAuthStateChanged(Firebase.auth)
        Firebase.auth.addAuthStateListener(listener)
    }

    override fun onCleared() {
        Firebase.auth.removeAuthStateListener(listener)
    }
    override fun onUserLoaded(user: User) {
        _user.value = user
    }
    override fun onCityAdded(city: City) {
        _cities[city.name] = city
    }
    override fun onCityRemoved(city: City) {
        _cities.remove(city.name)
    }

    override fun onUserSignOut() {
        TODO("Not yet implemented")
    }

    override fun onCityUpdated(city: City) {
        _cities.remove(city.name)
        _cities[city.name] = city.copy()
        if (_city.value?.name == city.name) {
            _city.value = city.copy(
                weather = if (city.weather != null) city.weather
                else _city.value?.weather,
                forecast = if (city.forecast != null) city.forecast
                else _city.value?.forecast
            )
        }
    }
}