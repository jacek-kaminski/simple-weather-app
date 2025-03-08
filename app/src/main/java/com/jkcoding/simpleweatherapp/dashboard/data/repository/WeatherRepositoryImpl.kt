package com.jkcoding.simpleweatherapp.dashboard.data.repository

import com.jkcoding.simpleweatherapp.dashboard.data.mappers.toWeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.data.remote.WeatherApi
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.domain.repository.WeatherRepository
import com.jkcoding.simpleweatherapp.dashboard.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat = latitude, long = longitude).toWeatherInfo()
            )
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }
}