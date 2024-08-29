package com.pdm.weatherapp.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherService {
    private var weatherAPI: WeatherServiceAPI
    init {
        val retrofitAPI = Retrofit.Builder()
            .baseUrl(WeatherServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofitAPI.create(WeatherServiceAPI::class.java)
    }

    private fun <T> enqueue(call: Call<T?>, onResponse: ((T?) -> Unit) ? = null) {
        call.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val obj: T? = response.body()
                onResponse?.invoke(obj)
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                Log.w("Weather WARNING", "" + t.message)
            }
        })
    }
    fun getName(lat: Double, lng: Double, onResponse : (String?) -> Unit ) {
        search("$lat,$lng") { loc -> onResponse (loc?.name) }
    }
    fun getLocation(name: String,
                    onResponse: (lat:Double?, long:Double?) -> Unit) {
        search(name) { loc -> onResponse (loc?.lat, loc?.lon) }
    }
    private fun search(query: String, onResponse : (APILocation?) -> Unit) {
        val call: Call<List<APILocation>?> = weatherAPI.search(query)
        enqueue(call = call) {
            if (it != null) {
                onResponse.invoke(it[0])
            }
        }
    }
}