package com.kawaki.weather.screens.search

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kawaki.weather.components.BottomBar
import com.kawaki.weather.components.TextBox
import com.kawaki.weather.model.MCity
import com.kawaki.weather.navigation.NavScreens
import com.kawaki.weather.screens.home.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navHostController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val searchState = remember { mutableStateOf("") }
    val localKeyboard = LocalSoftwareKeyboardController.current

    val dynamicTextColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.6f)
    val dynamicScaffoldColor = if (isSystemInDarkTheme()) Color.Black else Color.White

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navHostController = navHostController) },
        topBar = {
            Column(modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .height(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(modifier = Modifier.height(50.dp))

                    TextBox(searchState = searchState) {
                        if (searchState.value.isNotBlank()) {
                            viewModel.getWeather(city = searchState.value.trim())
                            localKeyboard?.hide()
                            viewModel.updateWeatherData(mCity = MCity(cityName = searchState.value.trim()))
                            navHostController.navigate(NavScreens.HomeScreen.route)
                        }
                    }
                }
            },
            containerColor = dynamicScaffoldColor) { innerPadding ->

            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(top = 50.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Search your city", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = dynamicTextColor, textAlign = TextAlign.Center))

            }
        }
}