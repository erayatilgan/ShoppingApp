package com.info.shoppingapp.presentation.screens.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.R
import com.info.shoppingapp.core.databases.SizeData
import com.info.shoppingapp.domain.entities.Product
import com.info.shoppingapp.presentation.components.TextButtonX
import com.info.shoppingapp.presentation.tiles.product.SizeItem
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme

class ProductDetail : ComponentActivity() {
    private val data: Product by lazy {
        intent?.getSerializableExtra(UrunAdi) as Product
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ShoppingAppTheme {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val navigationBackground = colors.surface
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isDark
                    )
                    systemUiController.setNavigationBarColor(
                        color = navigationBackground,
                        darkIcons = !isDark
                    )
                }
                Scaffold(bottomBar = {
                    BottomNavigationX()
                }, backgroundColor = colors.surface) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .background(colors.surface)
                    ) {
                        ProductContent()
                    }
                }
            }
        }
    }

    @Composable
    private fun ProductContent() {
        val scrollState = rememberScrollState()

        Box {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = data.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .aspectRatio(.7f)
                    )
                    Column {
                        Spacer(
                            modifier = Modifier
                                .aspectRatio(1.15f)
                        )
                        Surface(
                            shape = RoundedCornerShape(30.dp, 30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(Modifier.fillMaxSize().padding(vertical = 15.dp)) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp, horizontal = 25.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = data.title,
                                        style = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        color = colors.primary
                                    )
                                    Text(
                                        text = data.price.toString(),
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colors.primary
                                    )
                                }

                                Column(Modifier.padding(vertical = 25.dp, horizontal = 25.dp)) {
                                    Row(
                                        Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.description),
                                            style = TextStyle(
                                                fontSize = 17.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            color = colors.primary
                                        )
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowDown,
                                            contentDescription = null,
                                            tint = colors.primaryVariant
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = data.description,
                                        color = colors.primaryVariant,
                                        style = TextStyle(fontSize = 18.sp)
                                    )
                                }

                                Column {
                                    Text(
                                        text = stringResource(id = R.string.choose_size),
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = colors.primary,
                                        modifier = Modifier.padding(horizontal = 25.dp)
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    SizeX()
                                }
                            }
                        }
                    }
                }
            }
            TopAppBar(elevation = 0.dp,
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth(),
                backgroundColor = Color.Transparent,
                title = {},
                navigationIcon = {
                    FloatingActionButton(
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        backgroundColor = Color.Transparent,
                        onClick = { onBackPressedDispatcher.onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = colors.primary,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                },
                actions = {
                    FloatingActionButton(
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        backgroundColor = Color.Transparent,
                        onClick = { }) {
                        Icon(
                            imageVector = if (data.isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = colors.primary,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            )
        }
    }

    @Composable
    private fun BottomNavigationX() {
        BottomNavigation(
            backgroundColor = colors.surface,
            elevation = 0.dp,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .height(70.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButtonX(
                    text = stringResource(id = R.string.add_basket),
                    modifier = Modifier
                        .size(width = 260.dp, height = 90.dp)
                )
            }
        }
    }

    @Composable
    fun SizeX() {
        val sizex = remember { SizeData.sizeList }

        Box() {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 25.dp)
            ) {
                items(sizex) {
                    SizeItem(size = it)
                }
            }
        }
    }


    companion object {
        private const val UrunAdi = "urunid"
        fun newIntent(context: Context, data: Product) =
            Intent(context, ProductDetail::class.java).apply {
                putExtra(UrunAdi, data)
            }
    }
}