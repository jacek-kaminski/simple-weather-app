package com.jkcoding.simpleweatherapp.dashboard.domain.model

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val cloudCoverage: Double,
    val weatherConditions: WeatherConditions,
)
