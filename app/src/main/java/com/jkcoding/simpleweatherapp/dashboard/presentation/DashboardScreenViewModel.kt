package com.jkcoding.simpleweatherapp.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkcoding.simpleweatherapp.dashboard.domain.util.Resource
import com.jkcoding.simpleweatherapp.dashboard.domain.model.WeatherInfo
import com.jkcoding.simpleweatherapp.dashboard.domain.usecase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface DashboardScreenState {
    data object Loading : DashboardScreenState
    data class Initialized(val weatherInfo: WeatherInfo) : DashboardScreenState
    data class Error(val message: String) : DashboardScreenState
}

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DashboardScreenState>(DashboardScreenState.Loading)
    val state: StateFlow<DashboardScreenState> = _state

    fun loadWeatherInfo() {
        viewModelScope.launch {
            val data = getWeatherDataUseCase()
            _state.value = when (data) {
                is Resource.Success -> DashboardScreenState.Initialized(weatherInfo = data.data)
                is Resource.Error -> DashboardScreenState.Error(message = data.message)
            }
        }
    }
}
