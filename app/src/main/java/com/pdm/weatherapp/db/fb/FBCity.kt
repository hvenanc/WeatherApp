package com.pdm.weatherapp.db.fb

import com.google.android.gms.maps.model.LatLng
import com.pdm.weatherapp.model.City

class FBCity {
    var name : String? = null
    var lat : Double? = null
    var lng : Double? = null
    fun toCity(): City {
        val latlng = LatLng(lat ?: 0.0, lng ?: 0.0)
        return City(name!!, weather = null, location = latlng)
    }
}
fun City.toFBCity() : FBCity {
    val fbCity = FBCity()
    fbCity.name = this.name
    fbCity.lat = this.location?.latitude ?: 0.0
    fbCity.lng = this.location?.longitude ?: 0.0
    return fbCity
}