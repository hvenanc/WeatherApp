package com.pdm.weatherapp

import android.app.Application
import com.pdm.weatherapp.monitor.ForecastMonitor
import com.pdm.weatherapp.repo.Repository

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val monitor = ForecastMonitor(this)
        val repo = Repository(this, monitor)
    }
}