package com.info.shoppingapp.presentation.tiles.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.domain.entities.Product

@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoritesDetailListTile(detail: Product, onTap: (() -> Unit) = { }) {
    Surface(
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        color = colors.background,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onTap() }
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column {
                Box {
                    Image(
                        painter = painterResource(id = detail.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                    Surface(
                        shape = CircleShape,
                        color = colors.onSurface,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                            .clickable { }
                            .size(35.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Text(
                        text = detail.title,
                        style = TextStyle(color = colors.primaryVariant),
                        fontSize = 17.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$" + detail.price.toString(),
                            textAlign = TextAlign.Start,
                            color = colors.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }
}