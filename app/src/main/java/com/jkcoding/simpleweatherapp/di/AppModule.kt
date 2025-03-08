package com.jkcoding.simpleweatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jkcoding.simpleweatherapp.dashboard.data.location.LocationTrackerImpl
import com.jkcoding.simpleweatherapp.dashboard.data.remote.WeatherApi
import com.jkcoding.simpleweatherapp.dashboard.data.repository.WeatherRepositoryImpl
import com.jkcoding.simpleweatherapp.dashboard.domain.location.LocationTracker
import com.jkcoding.simpleweatherapp.dashboard.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideLocationTracker(
        app: Application,
        locationProviderClient: FusedLocationProviderClient
    ): LocationTracker {
        return LocationTrackerImpl(
            locationClient = locationProviderClient,
            application = app
        )
    }
}