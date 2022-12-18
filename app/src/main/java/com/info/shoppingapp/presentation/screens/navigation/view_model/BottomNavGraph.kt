package com.info.shoppingapp.presentation.screens.navigation.view_model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.info.shoppingapp.presentation.screens.navigation.screens.basket.BasketView
import com.info.shoppingapp.presentation.screens.navigation.screens.favorite.FavoriteScreen
import com.info.shoppingapp.presentation.screens.navigation.screens.home.HomeView
import com.info.shoppingapp.presentation.screens.navigation.screens.profile.ProfileScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Home.route
    ) {
        composable(route = NavigationScreen.Home.route) {
            HomeView()
        }
        composable(route = NavigationScreen.Favorite.route) {
            FavoriteScreen()
        }
        composable(route = NavigationScreen.Basket.route) {
            BasketView()
        }
        composable(route = NavigationScreen.Profile.route) {
            ProfileScreen()
        }
    }
}