package com.info.shoppingapp.presentation.screens.navigation.view_model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class NavigationScreen(
    val route: String,
    val icon: ImageVector,
) {
    object Home : NavigationScreen("home", Icons.Rounded.Home)
    object Favorite : NavigationScreen("favorite", Icons.Outlined.Favorite)
    object Basket : NavigationScreen("basket", Icons.Filled.ShoppingBag)
    object Profile : NavigationScreen("profile", Icons.Filled.Person)
}