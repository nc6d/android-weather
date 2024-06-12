package com.kawaki.weather.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.kawaki.weather.navigation.NavScreens
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kawaki.weather.Utils

@Composable
fun BottomBar(navHostController: NavHostController) {
    val itemsList = NavScreens.Items.list
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry.value?.destination

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(Alignment.CenterVertically),
        contentAlignment = Center) {

        Surface(modifier = Modifier
            .fillMaxWidth(0.60f)
            .height(80.dp),
            color = if (isSystemInDarkTheme()) Utils.customBlack else Color.White) {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Top,
                horizontalArrangement = Arrangement.SpaceAround) {
                itemsList.forEach { item ->

                    BottomBarItems(item = item, isSelected = currentScreen?.route == item.route, navHostController = navHostController)

                }
            }
        }
    }
}

@Composable
fun BottomBarItems(item: NavScreens, isSelected: Boolean, navHostController: NavHostController) {
    val iconColor = if (isSelected && isSystemInDarkTheme()) Color.White else if (isSelected && !isSystemInDarkTheme()) Color.Black else Color.Gray
    val interactionSource = MutableInteractionSource()
    Box(modifier = Modifier
        .size(50.dp)
        .padding(10.dp)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) { navHostController.navigate(item.route) }) {
        Icon(painter = painterResource(id = if (isSelected) item.iconFilled else item.iconOutlined), contentDescription = item.name, tint = iconColor)
    }
}