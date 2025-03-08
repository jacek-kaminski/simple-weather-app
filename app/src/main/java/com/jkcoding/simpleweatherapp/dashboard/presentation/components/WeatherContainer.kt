package com.jkcoding.simpleweatherapp.dashboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.typography
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
import com.jkcoding.simpleweatherapp.ui.lightGray
import com.jkcoding.simpleweatherapp.ui.white
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun DashboardWeatherContainer(
    title: String,
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalSpacing.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = dimensions.large),
    ) {
        Text(
            text = title,
            style = typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = white
            )
        )
        Spacer(modifier = Modifier.height(dimensions.extraSmall))
        Text(
            text = stringResource(
                id = R.string.today_hour,
                weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            ),
            style = typography.titleMedium.copy(color = lightGray)
        )
        Spacer(modifier = Modifier.height(height = dimensions.large))
        Image(
            painter = painterResource(id = weatherData.weatherConditions.iconRes),
            contentDescription = null,
            modifier = Modifier.width(width = dimensions.weatherIconSize)
        )
        Spacer(modifier = Modifier.height(height = dimensions.small))
        Text(
            text = weatherData.weatherConditions.description,
            style = typography.headlineMedium.copy(color = white)
        )
        Spacer(modifier = Modifier.height(height = dimensions.small))
        Text(
            text = stringResource(
                id = R.string.temperature_value_unit,
                weatherData.temperatureCelsius.roundToInt()
            ),
            style = typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = white
            )
        )
    }
}

@Preview
@Composable
fun DashboardWeatherContainerPreview() {
    SimpleWeatherAppTheme {
        DashboardWeatherContainer(
            title = "Local weather",
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