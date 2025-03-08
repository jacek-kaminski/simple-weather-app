package com.jkcoding.simpleweatherapp.dashboard.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}