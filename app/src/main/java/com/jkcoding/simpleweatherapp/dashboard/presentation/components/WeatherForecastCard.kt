package com.jkcoding.simpleweatherapp.dashboard.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherConditions
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherData
import com.jkcoding.simpleweatherapp.ui.LocalSpacing
import com.jkcoding.simpleweatherapp.ui.SimpleWeatherAppTheme
import com.jkcoding.simpleweatherapp.ui.lightBlueGray
import com.jkcoding.simpleweatherapp.ui.white
import java.time.LocalDateTime

@Composable
fun WeatherForecastCard(
    title: String,
    weatherDataPerDay: List<WeatherData>,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalSpacing.current

    Card(
        colors = CardDefaults.cardColors().copy(containerColor = lightBlueGray),
        shape = RoundedCornerShape(size = dimensions.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(all = dimensions.medium)
        ) {
            Text(
                text = title.uppercase(),
                style = typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = white,
                )
            )
            Spacer(modifier = Modifier.height(height = dimensions.medium))
            LazyRow {
                itemsIndexed(weatherDataPerDay) { index, weatherData ->
                    WeatherForecastItem(
                        weatherData = weatherData,
                        modifier = Modifier.height(dimensions.forecastItemHeight)
                    )
                    if (index < weatherDataPerDay.lastIndex) {
                        VerticalDivider(
                            modifier = Modifier
                                .height(dimensions.forecastItemHeight)
                                .padding(horizontal = dimensions.medium)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherForecastCardPreview() {
    SimpleWeatherAppTheme {
        WeatherForecastCard(
            title = "TODAY'S FORECAST", weatherDataPerDay = listOf(
                WeatherData(
                    time = LocalDateTime.of(2025, 1, 1, 0, 0),
                    temperatureCelsius = 5.0,
                    pressure = 1016.0,
                    windSpeed = 3.5,
                    humidity = 52.0,
                    cloudCoverage = 15.0,
                    weatherConditions = WeatherConditions.ClearSky(isDay = false)
                ),
                WeatherData(
                    time = LocalDateTime.of(2025, 1, 1, 2, 0),
                    temperatureCelsius = 4.0,
                    pressure = 1016.0,
                    windSpeed = 3.5,
                    humidity = 52.0,
                    cloudCoverage = 15.0,
                    weatherConditions = WeatherConditions.MainlyClear(isDay = false)
                ),
                WeatherData(
                    time = LocalDateTime.of(2025, 1, 1, 3, 0),
                    temperatureCelsius = 2.0,
                    pressure = 1016.0,
                    windSpeed = 3.5,
                    humidity = 52.0,
                    cloudCoverage = 15.0,
                    weatherConditions = WeatherConditions.Overcast
                ),
                WeatherData(
                    time = LocalDateTime.of(2025, 1, 1, 4, 0),
                    temperatureCelsius = 0.0,
                    pressure = 1016.0,
                    windSpeed = 3.5,
                    humidity = 52.0,
                    cloudCoverage = 15.0,
                    weatherConditions = WeatherConditions.Overcast
                ),
            )
        )
    }
}