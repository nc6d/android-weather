package com.kawaki.weather.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kawaki.weather.R
import com.kawaki.weather.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navHostController: NavHostController) {

    val dynamicTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val dynamicScaffoldColor = if (isSystemInDarkTheme()) Color.Black else Color.White

    val uriHandler = LocalUriHandler.current
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {  },
        bottomBar = { BottomBar(navHostController = navHostController) },
        containerColor = dynamicScaffoldColor) { innerPadding ->

            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(top = 50.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                //AppBar

                Image(painter = painterResource(id = R.drawable.about_pic), contentDescription = "Pic")
                Text(modifier = Modifier.padding(bottom = 20.dp), text = "This app is powered by the OpenWeather API and crafted with Android Jetpack Compose, is your go-to source for real-time weather updates. With Room Database integration, this app empower you to save your favorite locations and weather data anytime. Built with the modern MVVM (Model-View-ViewModel) architecture. Enjoy a beautifully designed interface, while having the flexibility to switch between light and dark modes.", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = dynamicTextColor, textAlign = TextAlign.Center))
                Image(modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .clickable { uriHandler.openUri("https://github.com/Kawaki22/") },
                    painter = painterResource(id = R.drawable.github), contentDescription = "GitHub", colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black))

            Spacer(modifier = Modifier.height(20.dp))
            }
        }
}