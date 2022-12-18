package com.info.shoppingapp.presentation.screens.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.info.shoppingapp.core.databases.ProductFakeData
import com.info.shoppingapp.domain.entities.Category
import com.info.shoppingapp.domain.entities.Product
import com.info.shoppingapp.domain.repositories.IProductRepository
import com.info.shoppingapp.infrastructure.data_sources.product.ProductFakeDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductLocalDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductRemoteDataSource
import com.info.shoppingapp.infrastructure.repositories.ProductRepository
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.screens.product.ProductDetail
import com.info.shoppingapp.presentation.tiles.categories.DetailListTile
import com.info.shoppingapp.presentation.ui.theme.ShoppingAppTheme

class CategoriesDetail : ComponentActivity() {
    private val data: Category by lazy {
        intent?.getSerializableExtra(UrunAdi) as Category
    }

    private val repository: IProductRepository = ProductRepository(
        ProductRemoteDataSource(),
        ProductLocalDataSource(),
        ProductFakeDataSource()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme() {
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
                Scaffold(backgroundColor = colors.background)
                {
                    Column(modifier = Modifier.padding(it)) {
                        DetailPage()
                    }
                }
            }
        }
    }

    @Composable
    fun DetailPage() {
//        val details = remember { repository.getProducts() }
        val details = remember { ProductFakeData.productList }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .statusBarsPadding()
        ) {
            Row(
                Modifier.fillMaxWidth(),
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
                    title = data.category,
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
                )
                Icon(
                    imageVector = Icons.Default.FilterAlt,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = details.size.toString() + " items...",
                color = colors.primaryVariant, fontSize = 18.sp
            )
        }
        DetailView()
    }

    @Composable
    fun DetailView() {
        Scaffold()
        { padding ->
//        val details = remember { repository.getProducts() }
            val details = remember { ProductFakeData.productList }
            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(top = 20.dp)
            ) {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    columns = GridCells.Adaptive(minSize = 150.dp)
                ) {
                    items(details) {
                        DetailListTile(detail = it, onTap = {
                            startActivity(ProductDetail.newIntent(applicationContext, it))
                        })
                    }
                }
            }
        }
    }

    companion object {
        private const val UrunAdi = "urunid"
        fun newIntent(context: Context, data: Category) =
            Intent(context, CategoriesDetail::class.java).apply {
                putExtra(UrunAdi, data)
            }
    }
}