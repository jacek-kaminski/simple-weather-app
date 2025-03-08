package com.jkcoding.simpleweatherapp.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Compress
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WindPower
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jkcoding.simpleweatherapp.R
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherConditions
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherData
import com.jkcoding.simpleweatherapp.ui.LocalSpacing
import com.jkcoding.simpleweatherapp.ui.SimpleWeatherAppTheme
import com.jkcoding.simpleweatherapp.ui.lightBlueGray
import com.jkcoding.simpleweatherapp.ui.white
import java.time.LocalDateTime
import kotlin.math.roundToInt

@Composable
fun WeatherConditionsCard(
    title: String,
    weatherData: WeatherData,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.weight(weight = 1f),
                    verticalArrangement = Arrangement.spacedBy(space = dimensions.medium)
                ) {
                    WeatherConditionsItem(
                        label = stringResource(id = R.string.air_pressure),
                        value = stringResource(
                            R.string.air_pressure_value_unit,
                            weatherData.pressure.roundToInt()
                        ),
                        icon = Icons.Outlined.Compress,
                    )
                    WeatherConditionsItem(
                        label = stringResource(id = R.string.humidity),
                        value = stringResource(
                            R.string.humidity_value_unit,
                            weatherData.humidity.roundToInt()
                        ),
                        icon = Icons.Outlined.WaterDrop,
                    )
                }
                Column(
                    modifier = Modifier.weight(weight = 1f),
                    verticalArrangement = Arrangement.spacedBy(space = dimensions.medium),
                ) {
                    WeatherConditionsItem(
                        label = stringResource(id = R.string.wind_speed),
                        value = stringResource(
                            R.string.wind_speed_value_unit,
                            weatherData.windSpeed.roundToInt()
                        ),
                        icon = Icons.Outlined.WindPower,
                    )
                    WeatherConditionsItem(
                        label = stringResource(id = R.string.cloud_coverage),
                        value = stringResource(
                            R.string.cloud_coverage_value_unit,
                            weatherData.cloudCoverage.roundToInt()
                        ),
                        icon = Icons.Outlined.Cloud,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherConditionsCardPreview() {
    SimpleWeatherAppTheme {
        WeatherConditionsCard(
            title = "WEATHER CONDITIONS",
            weatherData = WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 24.5,
                pressure = 1016.0,
                windSpeed = 3.5,
                humidity = 52.0,
                cloudCoverage = 15.0,
                weatherConditions = WeatherConditions.MainlyClear(isDay = true)
            )
        )
    }
}