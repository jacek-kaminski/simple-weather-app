package com.jkcoding.simpleweatherapp.dashboard.domain.usecase

import com.jkcoding.simpleweatherapp.dashboard.domain.util.Resource
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(): Resource<WeatherInfo> {
        return getCurrentLocationUseCase()?.let { location ->
            weatherRepository.getWeatherData(
                latitude = location.latitude,
                longitude = location.longitude
            )
        } ?: Resource.Error(message = "Couldn't retrieve location. Make sure to grant permission.")
    }
}