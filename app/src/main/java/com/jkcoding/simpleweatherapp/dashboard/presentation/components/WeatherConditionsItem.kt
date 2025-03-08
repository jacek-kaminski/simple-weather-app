package com.jkcoding.simpleweatherapp.dashboard.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jkcoding.simpleweatherapp.ui.LocalSpacing
import com.jkcoding.simpleweatherapp.ui.SimpleWeatherAppTheme
import com.jkcoding.simpleweatherapp.ui.lightGray
import com.jkcoding.simpleweatherapp.ui.white

@Composable
fun WeatherConditionsItem(
    label: String,
    value: String,
    icon: ImageVector,
) {
    val dimensions = LocalSpacing.current

    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = lightGray,
            modifier = Modifier.size(size = dimensions.conditionsItemIconSize)
        )
        Spacer(modifier = Modifier.width(width = dimensions.small))
        Column {
            Text(
                text = label,
                color = lightGray,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = value,
                color = white,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
fun WeatherConditionsItemPreview() {
    SimpleWeatherAppTheme {
        WeatherConditionsItem(
            label = "Wind",
            value = "0.2 km/h",
            icon = Icons.Outlined.WindPower,
        )
    }
}