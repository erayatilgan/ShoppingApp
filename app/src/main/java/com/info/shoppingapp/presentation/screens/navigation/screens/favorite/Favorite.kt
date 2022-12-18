package com.info.shoppingapp.presentation.screens.navigation.screens.favorite
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.core.databases.ProductFakeData
import com.info.shoppingapp.domain.repositories.IProductRepository
import com.info.shoppingapp.infrastructure.data_sources.product.ProductFakeDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductLocalDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductRemoteDataSource
import com.info.shoppingapp.infrastructure.repositories.ProductRepository
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.screens.product.ProductDetail
import com.info.shoppingapp.presentation.tiles.favorites.FavoritesDetailListTile

private val repository : IProductRepository = ProductRepository(ProductRemoteDataSource(), ProductLocalDataSource(), ProductFakeDataSource())

@Composable
fun FavoriteScreen() {
    DetailView()
}

@Composable
fun DetailView() {
    val context= LocalContext.current
    val favorite = ProductFakeData.productList.filter { it.isLiked==true }
    Box()
    {
            LazyVerticalGrid(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                columns = GridCells.Fixed(1)
            ) {
                items(favorite) {
                    FavoritesDetailListTile(detail = it, onTap = {
                        context.startActivity(ProductDetail.newIntent(context, it))
                    })
                }
            }
        }
    }
