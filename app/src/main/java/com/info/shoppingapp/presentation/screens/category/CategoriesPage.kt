package com.info.shoppingapp.presentation.screens.category

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.core.databases.CategoryFakeData
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.components.TextButtonX
import com.info.shoppingapp.presentation.screens.basket.BasketPage
import com.info.shoppingapp.presentation.screens.navigation.screens.basket.BasketView
import com.info.shoppingapp.presentation.tiles.categories.CategoriesListTile
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme

class CategoriesPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val color = colors.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = color,
                        darkIcons = !isDark
                    )
                    systemUiController.setNavigationBarColor(
                        color = color,
                        darkIcons = !isDark
                    )
                }
                Categories()
            }
        }
    }

    @SuppressLint("NotConstructor")
    @Composable
    fun Categories() {
        val context = LocalContext.current
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { onBackPressedDispatcher.onBackPressed() }
                    )
                    Subtitle(
                        title = "Categories",
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                context.startActivity(Intent(context,BasketPage::class.java))
                            }
                    )
                }
            },
            backgroundColor = colors.background,
        ) { it ->
            Box(modifier = Modifier
                .padding(it)
                .padding(horizontal = 15.dp)) {
                Column() {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        var selectedIndex by remember {mutableStateOf(0)}

                        TabButton(text = "Woman", selected = selectedIndex == 0) {
                            selectedIndex = 0
                        }
                        TabButton(text = "Man", selected = selectedIndex == 1) {
                            selectedIndex = 1
                        }
                    }
                    Box(Modifier.fillMaxWidth()) {
                        val data = remember { CategoryFakeData.categoryList }
                        val context = LocalContext.current
                        LazyColumn(
                            userScrollEnabled = false,
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                        ) {
                            items(data) {
                                CategoriesListTile(category = it, onTap = {
                                    context.startActivity(CategoriesDetail.newIntent(context, it))
                                })
                            }
                        }
                    }
                }
            }
        }
    }
    @Composable
    private fun TabButton(text:String, selected:Boolean, onTap: () -> Unit){
        val borderColor = if(selected) Color.Black else Color.Transparent
        Button(
            onClick = onTap,
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(colors.background),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
