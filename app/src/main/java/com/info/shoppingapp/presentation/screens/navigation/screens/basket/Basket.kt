package com.info.shoppingapp.presentation.screens.navigation.screens.basket

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.R
import com.info.shoppingapp.core.databases.BasketFakeData
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.components.TextButtonX
import com.info.shoppingapp.presentation.tiles.basket.BasketItemList

@Composable
fun BasketView() {
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
                            .clickable { }
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
        backgroundColor = colors.background,
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
                                    total = productList.map { it.amount * it.product.price }.sum()
                                    product.amount
                                }, onPlusTap = {
                                    ++product.amount
                                    total = productList.map { it.amount * it.product.price }.sum()
                                    product.amount
                                })
                            }
                        }
                    }
                    Column {
                        Spacer(
                            modifier = Modifier
                                .aspectRatio(1.35f)
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
                                        color = colors.primary
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
                                            color = colors.primary
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
                                            color = colors.primary
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
        backgroundColor = colors.surface,
        elevation = 0.dp,
        modifier = Modifier
            .height(70.dp)
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