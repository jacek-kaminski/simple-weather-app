package com.jkcoding.simpleweatherapp.dashboard.domain.usecase

import android.location.Location
import com.jkcoding.simpleweatherapp.dashboard.domain.location.LocationTracker
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationTracker: LocationTracker,
) {
    suspend operator fun invoke(): Location? {
        return locationTracker.getCurrentLocation()
    }
}