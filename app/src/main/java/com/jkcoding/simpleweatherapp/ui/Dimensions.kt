package com.jkcoding.simpleweatherapp.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val weatherIconSize: Dp = 200.dp,
    val forecastItemIconSize: Dp = 40.dp,
    val forecastItemHeight: Dp = 100.dp,
    val conditionsItemIconSize: Dp = 24.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
