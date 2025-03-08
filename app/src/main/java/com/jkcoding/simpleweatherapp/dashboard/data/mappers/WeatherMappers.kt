package com.jkcoding.simpleweatherapp.dashboard.data.mappers

import com.jkcoding.simpleweatherapp.dashboard.data.remote.WeatherDataDto
import com.jkcoding.simpleweatherapp.dashboard.data.remote.WeatherDto
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherConditions
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherData
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->

        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        val cloudCoverage = cloudCoverages[index]

        val parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = parsedTime,
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                cloudCoverage = cloudCoverage,
                weatherConditions = WeatherConditions.fromWMOCode(
                    code = weatherCode,
                    isDay = parsedTime.hour in 5..22
                ),
            )
        )
    }
        .groupBy { indexedData -> indexedData.index / 24 }
        .mapValues { mapEntry -> mapEntry.value.map { indexedData -> indexedData.data } }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
