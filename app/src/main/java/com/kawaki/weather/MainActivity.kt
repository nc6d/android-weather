package com.kawaki.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.kawaki.weather.navigation.WeatherAppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor =
                if (isSystemInDarkTheme()) Utils.customBlack.toArgb() else Color.White.toArgb()
            window.navigationBarColor =
                if (isSystemInDarkTheme()) Utils.customBlack.toArgb() else Color.White.toArgb()
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    WeatherAppNavigation()
}