package com.kawaki.weather.network

import com.kawaki.weather.Utils
import com.kawaki.weather.model.MWeather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Utils.API_KEY
        ): MWeather
}