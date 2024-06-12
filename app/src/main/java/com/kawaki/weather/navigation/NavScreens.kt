package com.kawaki.weather.navigation

import com.kawaki.weather.R

sealed class NavScreens(
    val route: String,
    val name: String,
    val iconOutlined: Int,
    val iconFilled: Int
) {
    data object HomeScreen : NavScreens(
        route = "HomeScreen", name = "Home",
        iconOutlined = R.drawable.home_outlined, iconFilled = R.drawable.home_filled
    )

    data object SearchScreen : NavScreens(
        route = "SearchScreen", name = "Search",
        iconOutlined = R.drawable.search_outlined, iconFilled = R.drawable.search_filled
    )

    data object AboutScreen : NavScreens(
        route = "AboutScreen", name = "About",
        iconOutlined = R.drawable.about_outlined, iconFilled = R.drawable.about_filled
    )

    object Items {
        val list = listOf(
            HomeScreen,
            SearchScreen,
            AboutScreen
        )
    }
}
