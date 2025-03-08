package com.jkcoding.simpleweatherapp.dashboard.domain.repository

import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}
