package com.kawaki.weather.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kawaki.weather.Utils
import com.kawaki.weather.network.WeatherApi
import com.kawaki.weather.room.WeatherDao
import com.kawaki.weather.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl(Utils.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext app: Context): WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "weather_database").fallbackToDestructiveMigration().build()
    }

}