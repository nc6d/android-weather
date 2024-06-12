package com.kawaki.weather.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kawaki.weather.model.MCity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table")
    suspend fun getWeatherData(): MCity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherData(mCity: MCity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWeatherData(mCity: MCity)
}