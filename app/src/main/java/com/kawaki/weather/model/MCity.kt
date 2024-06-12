package com.kawaki.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class MCity(
    @PrimaryKey
    val pKey: Int = 1,
    val cityName: String
)
