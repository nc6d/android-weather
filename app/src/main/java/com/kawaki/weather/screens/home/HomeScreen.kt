package com.kawaki.weather.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kawaki.weather.R
import com.kawaki.weather.components.BottomBar
import com.kawaki.weather.components.LoadingComp
import com.kawaki.weather.components.WeatherCards
import com.kawaki.weather.model.MCity
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val weatherData = viewModel.weatherData.collectAsState().value.data
    val weatherDataCity = viewModel.weatherDataCity.collectAsState()

    val dateTime = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
    val simpleDateTime = dateTime.format(Date()).toString()

    val dynamicTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val dynamicScaffoldColor = if (isSystemInDarkTheme()) Color.Black else Color.White


    //Creating Weather Room
    LaunchedEffect(key1 = true) {
        delay(1000)
        if (weatherDataCity.value == null) {
            viewModel.addWeatherData(mCity = MCity(cityName = "Kyiv"))
        }
    }

    //Searching Weather using city name from Room
    LaunchedEffect(key1 = weatherDataCity.value) {
        weatherDataCity.value?.cityName?.let { viewModel.getWeather(city = it) }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navHostController = navHostController) },
        containerColor = dynamicScaffoldColor) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (weatherData != null) {

                    val weatherIcon = when(weatherData.weather.first().icon) {
                        "01d", "01n" -> R.drawable.clear_sky
                        "02d", "02n" -> R.drawable.few_clouds
                        "03d", "03n" -> R.drawable.scattered_broken_clouds
                        "04d", "04n" -> R.drawable.scattered_broken_clouds
                        "09d", "09n" -> R.drawable.rain
                        "10d", "10n" -> R.drawable.rain
                        "11d", "11n" -> R.drawable.thunderstorm
                        "13d", "13n" -> R.drawable.snow
                        "50d", "50n" -> R.drawable.mist
                        else -> R.drawable.clear_sky
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //City name
                    Text(
                        text = " ${weatherData.name}",
                        style = TextStyle(fontSize = 42.sp, fontWeight = FontWeight.Bold, color = dynamicTextColor
                        )
                    )

                    Text(
                        text = "Today's Weather",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, color = dynamicTextColor
                        ),
                        modifier = Modifier.padding(top = 10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp, top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                    
                        Image(
                            painter = painterResource(id = weatherIcon),
                            contentDescription = weatherData.weather.first().description,
                            modifier = Modifier
                        )

                        Text(text = "${weatherData.main.temp.toString().substringBefore(".")}°c",
                            style = TextStyle(fontSize = 55.sp, fontWeight = FontWeight.Bold, color = dynamicTextColor)
                        )
                    }
                    
                    WeatherCards(
                        weatherData = weatherData,
                        title = weatherData.weather.first().description,
                        date = simpleDateTime
                    )

                } else {
                    LoadingComp()
                }
            }
    }
}

@Preview
@Composable
fun Prev() {
    HomeScreen(navHostController = rememberNavController())
}