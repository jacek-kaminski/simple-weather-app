package com.jkcoding.simpleweatherapp.dashboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jkcoding.simpleweatherapp.R
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherConditions
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherData
import com.jkcoding.simpleweatherapp.ui.LocalSpacing
import com.jkcoding.simpleweatherapp.ui.SimpleWeatherAppTheme
import com.jkcoding.simpleweatherapp.ui.white
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherForecastItem(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalSpacing.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            color = white,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = weatherData.weatherConditions.iconRes),
            contentDescription = null,
            modifier = Modifier.width(width = dimensions.forecastItemIconSize)
        )
        Text(
            text = stringResource(
                id = R.string.temperature_value_unit,
                weatherData.temperatureCelsius.roundToInt(),
            ),
            color = white,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun WeatherForecastItemPreview() {
    SimpleWeatherAppTheme {
        WeatherForecastItem(
            weatherData = WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 24.5,
                pressure = 1016.0,
                windSpeed = 3.5,
                humidity = 52.0,
                cloudCoverage = 15.0,
                weatherConditions = WeatherConditions.MainlyClear(isDay = true)
            ),
        )
    }
}
