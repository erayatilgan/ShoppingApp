package com.info.shoppingapp.presentation.tiles.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.domain.entities.BasketProduct
import com.info.shoppingapp.domain.entities.Product

@Composable
fun BasketItemList(data: BasketProduct, onPlusTap: () -> Int, onMinusTap: () -> Int) {
    var value by remember { mutableStateOf(data.amount) }

    Surface(
        color = colors.background,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxSize()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Surface(
                    modifier = Modifier
                        .size(83.dp)
                        .clip(RoundedCornerShape(15.dp)),
                )
                {
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                        painter = painterResource(id = data.product.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                }
                Column() {
                    Column(Modifier.padding(vertical = 5.dp)) {
                        Text(
                            text = data.product.title,
                            color = colors.primaryVariant,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "(M)", color = colors.primaryVariant,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Text(
                        text = "$ " + data.amount * data.product.price, color = colors.primary,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }

            Column()
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = {
                            value = onMinusTap.invoke()
                        }
                    ) {
                        Text(
                            text = "-", color = Color.Gray, fontSize = 30.sp
                        )
                    }

                    Text(
                        text = value.toString(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp
                    )

                    TextButton(onClick = {
                        value = onPlusTap.invoke()
                    }) {
                        Text(
                            text = "+", color = Color.Gray, fontSize = 30.sp
                        )
                    }
                }
            }
        }
    }
}