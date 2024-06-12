package com.kawaki.weather.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kawaki.weather.R
import com.kawaki.weather.Utils
import com.kawaki.weather.model.MWeather
import java.util.Locale

@Composable
fun WeatherCards(weatherData: MWeather, title: String, date: String) {

    val dynamicTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val dynamicCardColor = if (isSystemInDarkTheme()) Utils.customLightBlack else Utils.customLightWhite

    Surface(modifier = Modifier
        .fillMaxWidth(0.90f)
        .fillMaxHeight(),
        shape = RoundedCornerShape(45.dp),
        color = dynamicCardColor
    ) {
        Text(text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = dynamicTextColor, textAlign = TextAlign.Center), modifier = Modifier.padding(top = 10.dp))

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 40.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(30.dp))
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = date, style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = dynamicTextColor, textAlign = TextAlign.Center))


            CardItems(title = "Wind", subtitle = "${weatherData.wind.speed} Km/h", icon = R.drawable.wind, color = dynamicTextColor)
            CardItems(title = "Wind Gust", subtitle = "${weatherData.wind.gust} Km/h", icon = R.drawable.wind_gust, color = dynamicTextColor)
            CardItems(title = "Humidity", subtitle = "${weatherData.main.humidity}%", icon = R.drawable.humidity, color = dynamicTextColor)
            CardItems(title = "Pressure", subtitle = "${weatherData.main.pressure} mb", icon = R.drawable.air_pressure, color = dynamicTextColor)
            CardItems(title = "Visibility", subtitle = "${weatherData.visibility} Km", icon = R.drawable.visibility, color = dynamicTextColor)


//            LazyColumn(contentPadding = PaddingValues(top = 10.dp)) {
//                item {
//                    CardItems(title = "Wind", subtitle = "${weatherData.wind.speed} Km/h", icon = R.drawable.wind, color = dynamicTextColor)
//                }
//                item {
//                    CardItems(title = "Wind Gust", subtitle = "${weatherData.wind.gust} Km/h", icon = R.drawable.wind_gust, color = dynamicTextColor)
//                }
//                item {
//                    CardItems(title = "Humidity", subtitle = "${weatherData.main.humidity}%", icon = R.drawable.humidity, color = dynamicTextColor)
//                }
//                item {
//                    CardItems(title = "Pressure", subtitle = "${weatherData.main.pressure} mb", icon = R.drawable.air_pressure, color = dynamicTextColor)
//                }
//                item {
//                    CardItems(title = "Visibility", subtitle = "${weatherData.visibility} Km", icon = R.drawable.visibility, color = dynamicTextColor)
//                }
//            }
//            Icon(painter = painterResource(id = icon), contentDescription = title, tint = Color.White, modifier = Modifier.size(25.dp))
//            Text(text = title, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White))
//            Text(text = subtitle, style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.White))
        }
    }
}

@Composable
fun CardItems(modifier: Modifier = Modifier, title: String, subtitle: String, icon: Int, color: Color) {

    Surface(modifier = modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(20.dp),
        color = if (isSystemInDarkTheme()) Utils.customLightBlack2 else Utils.customLightWhite2
    ) {

        Row(modifier = modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = icon), contentDescription = title, modifier = Modifier.size(35.dp), tint = if (isSystemInDarkTheme()) Color.White else Color.Black)

            Column(modifier = Modifier.fillMaxWidth(0.50f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = title, style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = color))
                Text(text = subtitle, style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = color))
            }
        }
    }
}

@Preview
@Composable
fun Prev() {
//    WeatherCards(title = "Scattered clouds", subtitle = "", icon = 0)
}