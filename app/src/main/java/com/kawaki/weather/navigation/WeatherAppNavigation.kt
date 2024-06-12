package com.kawaki.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kawaki.weather.screens.home.HomeScreen
import com.kawaki.weather.screens.about.AboutScreen
import com.kawaki.weather.screens.search.SearchScreen

@Composable
fun WeatherAppNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavScreens.HomeScreen.route) {

        composable(NavScreens.HomeScreen.route) {
                HomeScreen(navHostController = navHostController)
        }
        composable(NavScreens.SearchScreen.route) {
            SearchScreen(navHostController = navHostController)
        }
        composable(NavScreens.AboutScreen.route) {
            AboutScreen(navHostController = navHostController)
        }
    }
}