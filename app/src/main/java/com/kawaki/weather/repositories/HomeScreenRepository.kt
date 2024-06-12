package com.kawaki.weather.repositories

import com.kawaki.weather.data.DataOrException
import com.kawaki.weather.model.MCity
import com.kawaki.weather.model.MWeather
import com.kawaki.weather.network.WeatherApi
import com.kawaki.weather.room.WeatherDao
import javax.inject.Inject

class HomeScreenRepository @Inject constructor(private val weatherApi: WeatherApi, private val dao: WeatherDao) {

    suspend fun getWeather(city: String): DataOrException<MWeather, Boolean, Exception> {
        val dataOrException = DataOrException<MWeather, Boolean, Exception>()

        try {
            dataOrException.data = weatherApi.getWeather(query = city)
        } catch (ex: Exception) {
            dataOrException.e = ex
        }
        return dataOrException
    }

    suspend fun getWeatherData(): MCity = dao.getWeatherData()
    suspend fun addWeatherData(mCity: MCity) = dao.addWeatherData(mCity = mCity)
    suspend fun updateWeatherData(mCity: MCity) = dao.updateWeatherData(mCity = mCity)
}