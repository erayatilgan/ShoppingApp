package com.info.shoppingapp.presentation.screens.navigation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.presentation.screens.navigation.view_model.BottomNavGraph
import com.info.shoppingapp.presentation.screens.navigation.view_model.NavigationScreen
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingAppTheme() {
                val navController = rememberNavController()

                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val systemBarColor = colors.background
                val navigationBarColor = colors.primary

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarColor,
                        darkIcons = !isDark
                    )
                    systemUiController.setNavigationBarColor(
                        color = navigationBarColor,
                        darkIcons = !isDark
                    )
                }

                Scaffold(bottomBar = {
                    BottomBar(navController = navController)
                }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        BottomNavGraph(navController = navController)
                    }
                }

            }
        }
    }

    @Composable
    fun BottomBar(navController: NavController) {
        val screens = listOf(
            NavigationScreen.Home,
            NavigationScreen.Favorite,
            NavigationScreen.Basket,
            NavigationScreen.Profile
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomNavigation(
            backgroundColor = colors.primary,
            elevation = 0.dp,
            modifier = Modifier
                .height(65.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun RowScope.AddItem(
        screen: NavigationScreen,
        currentDestination: NavDestination?,
        navController: NavController
    ) {
        val isSelected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true

        BottomNavigationItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.weight(1f)) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "Navigation Icon",
                            modifier = Modifier.size(26.dp).align(Alignment.Center),
                        )
                    }
                    Icon(
                        imageVector = Icons.Rounded.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(8.dp),
                        tint = if(isSelected) colors.onPrimary else Color.Transparent
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            },
            selected = isSelected,
            unselectedContentColor = colors.primaryVariant,
            selectedContentColor = colors.onPrimary,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        )
    }
}

