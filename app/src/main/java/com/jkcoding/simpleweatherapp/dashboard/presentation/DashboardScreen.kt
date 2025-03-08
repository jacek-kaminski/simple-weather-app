package com.jkcoding.simpleweatherapp.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jkcoding.simpleweatherapp.R
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherConditions
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherData
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.presentation.components.DashboardWeatherContainer
import com.jkcoding.simpleweatherapp.dashboard.presentation.components.WeatherConditionsCard
import com.jkcoding.simpleweatherapp.dashboard.presentation.components.WeatherForecastCard
import com.jkcoding.simpleweatherapp.ui.LocalSpacing
import com.jkcoding.simpleweatherapp.ui.SimpleWeatherAppTheme
import com.jkcoding.simpleweatherapp.ui.darkBlueGray
import com.jkcoding.simpleweatherapp.ui.red
import java.time.LocalDateTime

@Composable
fun DashboardScreen(
    dashboardScreenState: DashboardScreenState
) {
    when (dashboardScreenState) {
        DashboardScreenState.Loading -> DashboardLoadingContent()
        is DashboardScreenState.Initialized -> DashboardInitializedContent(state = dashboardScreenState)
        is DashboardScreenState.Error -> DashboardErrorContent(message = dashboardScreenState.message)
    }
}

@Composable
fun DashboardLoadingContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun DashboardInitializedContent(state: DashboardScreenState.Initialized) {
    val dimensions = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = darkBlueGray)
            .padding(all = dimensions.medium)
            .verticalScroll(rememberScrollState())
    ) {
        state.weatherInfo.currentWeatherData?.let {
            DashboardWeatherContainer(
                title = stringResource(id = R.string.dashboard_title),
                weatherData = it,
            )
        }
        state.weatherInfo.weatherDataPerDay[0]?.let {
            WeatherForecastCard(
                title = stringResource(id = R.string.forecast_today),
                weatherDataPerDay = it
            )
            Spacer(
                modifier = Modifier.height(height = dimensions.medium)
            )
        }
        state.weatherInfo.currentWeatherData?.let {
            WeatherConditionsCard(
                title = stringResource(id = R.string.weather_conditions),
                weatherData = state.weatherInfo.currentWeatherData
            )
        }
    }
}

@Composable
fun DashboardErrorContent(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = message,
            color = red,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.Center)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenLoadingPreview() {
    SimpleWeatherAppTheme {
        DashboardScreen(
            dashboardScreenState = DashboardScreenState.Loading
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenErrorPreview() {
    SimpleWeatherAppTheme {
        DashboardScreen(
            dashboardScreenState = DashboardScreenState.Error(message = "Some error message")
        )
    }
}

@Preview
@Composable
fun DashboardScreenInitializedPreview() {
    SimpleWeatherAppTheme {
        DashboardScreen(
            dashboardScreenState = DashboardScreenState.Initialized(
                weatherInfo = WeatherInfo(
                    currentWeatherData = WeatherData(
                        time = LocalDateTime.of(2025, 1, 1, 0, 0),
                        temperatureCelsius = 5.0,
                        pressure = 1016.0,
                        windSpeed = 3.5,
                        humidity = 52.0,
                        cloudCoverage = 15.0,
                        weatherConditions = WeatherConditions.ClearSky(isDay = false)
                    ),
                    weatherDataPerDay = mapOf(
                        0 to listOf(
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
                        ),
                    )
                )
            ),
        )
    }
}
