package com.info.shoppingapp.presentation.screens.basket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.core.databases.BasketFakeData
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.components.TextButtonX
import com.info.shoppingapp.presentation.tiles.basket.BasketItemList
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme

class BasketPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val statusColor = MaterialTheme.colors.background
                val navigationColor = MaterialTheme.colors.surface

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = statusColor,
                        darkIcons = !isDark
                    )
                    systemUiController.setNavigationBarColor(
                        color = navigationColor,
                        darkIcons = !isDark
                    )
                }
                Basket()
            }
        }
    }

    @Composable
    private fun Basket() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { onBackPressedDispatcher.onBackPressed() }
                        )
                    }
                    Column(Modifier.weight(1.5f)) {
                        Subtitle(
                            title = "Basket",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                        )
                    }
                }
            },
            bottomBar = { BottomNavigationX() },
            backgroundColor = MaterialTheme.colors.background,
        ) { it ->
            val productList = remember { BasketFakeData.itemsList }
            var total by remember {
                mutableStateOf(productList.map { it.amount * it.product.price }.sum())
            }
            Box(Modifier.padding(it)) {
                Column {
                    Box {
                        Box(Modifier.fillMaxWidth()) {

                            LazyColumn(
                                userScrollEnabled = false,
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 35.dp)
                            ) {
                                items(productList) { product ->
                                    BasketItemList(data = product, onMinusTap = {
                                        if (product.amount != 1)
                                            --product.amount
                                        total =
                                            productList.map { it.amount * it.product.price }.sum()
                                        product.amount
                                    }, onPlusTap = {
                                        ++product.amount
                                        total =
                                            productList.map { it.amount * it.product.price }.sum()

                                        product.amount
                                    })
                                }
                            }
                        }
                        Column {
                            Spacer(
                                modifier = Modifier
                                    .aspectRatio(1.2f)
                            )
                            Surface(
                                shape = RoundedCornerShape(30.dp, 30.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 15.dp)
                                ) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 5.dp, horizontal = 25.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Order Info",
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }

                                    Column(Modifier.padding(vertical = 25.dp, horizontal = 25.dp)) {
                                        Row(
                                            Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "Bright beret (M)",
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                                color = Color.DarkGray
                                            )
                                            Text(
                                                text = "2 item",
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                                color = Color.DarkGray
                                            )
                                        }

                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 15.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "Delivery",
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                                color = Color.DarkGray
                                            )
                                            Text(
                                                text = "$ 10.00",
                                                style = TextStyle(
                                                    fontSize = 25.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                color = MaterialTheme.colors.primary
                                            )
                                        }

                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "Price",
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                                color = Color.DarkGray
                                            )
                                            Text(
                                                text = "$ $total",
                                                style = TextStyle(
                                                    fontSize = 25.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                color = MaterialTheme.colors.primary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomNavigationX() {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            modifier = Modifier.height(70.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButtonX(
                    text = "Placing an Order",
                    modifier = Modifier
                        .size(width = 260.dp, height = 90.dp)
                )
            }
        }
    }
}
