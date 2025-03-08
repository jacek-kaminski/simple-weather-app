package com.jkcoding.simpleweatherapp.dashboard.domain.model

import androidx.annotation.DrawableRes
import com.jkcoding.simpleweatherapp.R

sealed class WeatherConditions(
    val description: String,
    @DrawableRes val iconRes: Int
) {

    data class ClearSky(val isDay: Boolean) : WeatherConditions(
        description = "Clear sky",
        iconRes = if (isDay) R.drawable.ic_clear_sky_day else R.drawable.ic_clear_sky_night
    )

    data class MainlyClear(val isDay: Boolean) : WeatherConditions(
        description = "Mainly clear",
        iconRes = if (isDay) R.drawable.ic_partial_cloud_day else R.drawable.ic_partial_cloud_night
    )

    data class PartlyCloudy(val isDay: Boolean) : WeatherConditions(
        description = "Partly cloudy",
        iconRes = if (isDay) R.drawable.ic_partial_cloud_day else R.drawable.ic_partial_cloud_night
    )

    data object Overcast : WeatherConditions(
        description = "Overcast",
        iconRes = R.drawable.ic_overcast
    )

    data object Foggy : WeatherConditions(
        description = "Foggy",
        iconRes = R.drawable.ic_fog
    )

    data object DepositingRimeFog : WeatherConditions(
        description = "Depositing rime fog",
        iconRes = R.drawable.ic_fog
    )

    data class LightDrizzle(val isDay: Boolean) : WeatherConditions(
        description = "Light drizzle",
        iconRes = if (isDay) R.drawable.ic_partial_rain_day else R.drawable.ic_partial_rain_night
    )

    data class ModerateDrizzle(val isDay: Boolean) : WeatherConditions(
        description = "Moderate drizzle",
        iconRes = if (isDay) R.drawable.ic_partial_rain_day else R.drawable.ic_partial_rain_night
    )

    data class DenseDrizzle(val isDay: Boolean) : WeatherConditions(
        description = "Dense drizzle",
        iconRes = if (isDay) R.drawable.ic_partial_rain_day else R.drawable.ic_partial_rain_night
    )

    data class LightFreezingDrizzle(val isDay: Boolean) : WeatherConditions(
        description = "Slight freezing drizzle",
        iconRes = if (isDay) R.drawable.ic_partial_sleet_day else R.drawable.ic_partial_sleet_night
    )

    data object DenseFreezingDrizzle : WeatherConditions(
        description = "Dense freezing drizzle",
        iconRes = R.drawable.ic_sleet
    )

    data class SlightRain(val isDay: Boolean) : WeatherConditions(
        description = "Slight rain",
        iconRes = if (isDay) R.drawable.ic_partial_rain_day else R.drawable.ic_partial_rain_day
    )

    data object ModerateRain : WeatherConditions(
        description = "Rainy",
        iconRes = R.drawable.ic_rain
    )

    data object HeavyRain : WeatherConditions(
        description = "Heavy rain",
        iconRes = R.drawable.ic_rain
    )

    data object HeavyFreezingRain : WeatherConditions(
        description = "Heavy freezing rain",
        iconRes = R.drawable.ic_sleet
    )

    data class SlightSnowFall(val isDay: Boolean) : WeatherConditions(
        description = "Slight snow fall",
        iconRes = if (isDay) R.drawable.ic_partial_snow_day else R.drawable.ic_partial_snow_night
    )

    data object ModerateSnowFall : WeatherConditions(
        description = "Moderate snow fall",
        iconRes = R.drawable.ic_snow
    )

    data object HeavySnowFall : WeatherConditions(
        description = "Heavy snow fall",
        iconRes = R.drawable.ic_snow
    )

    data object SnowGrains : WeatherConditions(
        description = "Snow grains",
        iconRes = R.drawable.ic_snow
    )

    data class SlightRainShowers(val isDay: Boolean) : WeatherConditions(
        description = "Slight rain showers",
        iconRes = if (isDay) R.drawable.ic_partial_rain_day else R.drawable.ic_partial_rain_night
    )

    data object ModerateRainShowers : WeatherConditions(
        description = "Moderate rain showers",
        iconRes = R.drawable.ic_rain
    )

    data object ViolentRainShowers : WeatherConditions(
        description = "Violent rain showers",
        iconRes = R.drawable.ic_rain
    )

    data class SlightSnowShowers(val isDay: Boolean) : WeatherConditions(
        description = "Light snow showers",
        iconRes = if (isDay) R.drawable.ic_partial_snow_day else R.drawable.ic_partial_snow_night
    )

    data object HeavySnowShowers : WeatherConditions(
        description = "Heavy snow showers",
        iconRes = R.drawable.ic_snow
    )

    data object ModerateThunderstorm : WeatherConditions(
        description = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder
    )

    data object SlightHailThunderstorm : WeatherConditions(
        description = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rain_thunder
    )

    data object HeavyHailThunderstorm : WeatherConditions(
        description = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rain_thunder
    )

    companion object {
        fun fromWMOCode(code: Int, isDay: Boolean) = when (code) {
            0 -> ClearSky(isDay)
            1 -> MainlyClear(isDay)
            2 -> PartlyCloudy(isDay)
            3 -> Overcast
            45 -> Foggy
            48 -> DepositingRimeFog
            51 -> LightDrizzle(isDay)
            53 -> ModerateDrizzle(isDay)
            55 -> DenseDrizzle(isDay)
            56 -> LightFreezingDrizzle(isDay)
            57 -> DenseFreezingDrizzle
            61 -> SlightRain(isDay)
            63 -> ModerateRain
            65 -> HeavyRain
            66 -> LightFreezingDrizzle(isDay)
            67 -> HeavyFreezingRain
            71 -> SlightSnowFall(isDay)
            73 -> ModerateSnowFall
            75 -> HeavySnowFall
            77 -> SnowGrains
            80 -> SlightRainShowers(isDay)
            81 -> ModerateRainShowers
            82 -> ViolentRainShowers
            85 -> SlightSnowShowers(isDay)
            86 -> HeavySnowShowers
            95 -> ModerateThunderstorm
            96 -> SlightHailThunderstorm
            99 -> HeavyHailThunderstorm
            else -> ClearSky(isDay)
        }
    }
}
