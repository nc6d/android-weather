package com.kawaki.weather.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawaki.weather.data.DataOrException
import com.kawaki.weather.model.MCity
import com.kawaki.weather.model.MWeather
import com.kawaki.weather.repositories.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeScreenRepository: HomeScreenRepository): ViewModel() {

    private val _weatherData: MutableStateFlow<DataOrException<MWeather, Boolean, Exception>> =
        MutableStateFlow(DataOrException(null, true, Exception("")))

    val weatherData = _weatherData

    private val _weatherDataCity: MutableStateFlow<MCity?> = MutableStateFlow(null)
    val weatherDataCity = _weatherDataCity

    init {
        getWeatherData()
    }

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherData.value = homeScreenRepository.getWeather(city = city)
            if (_weatherData.value.data != null) _weatherData.value.loading = false
        }
    }

    private fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherDataCity.value = homeScreenRepository.getWeatherData()
        }
    }

    fun addWeatherData(mCity: MCity) {
        viewModelScope.launch(Dispatchers.IO) {
            homeScreenRepository.addWeatherData(mCity = mCity)
        }
    }

    fun updateWeatherData(mCity: MCity) {
        viewModelScope.launch(Dispatchers.IO) {
            homeScreenRepository.updateWeatherData(mCity = mCity)
        }
    }
}