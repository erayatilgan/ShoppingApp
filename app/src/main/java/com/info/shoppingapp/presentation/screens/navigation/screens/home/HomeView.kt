package com.info.shoppingapp.presentation.screens.navigation.screens.home
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Percent
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.R
import com.info.shoppingapp.core.databases.CategoryFakeData
import com.info.shoppingapp.core.databases.ProductFakeData
import com.info.shoppingapp.domain.repositories.IProductRepository
import com.info.shoppingapp.infrastructure.data_sources.product.ProductFakeDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductLocalDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductRemoteDataSource
import com.info.shoppingapp.infrastructure.repositories.ProductRepository
import com.info.shoppingapp.presentation.components.Subtitle
import com.info.shoppingapp.presentation.screens.category.CategoriesPage
import com.info.shoppingapp.presentation.screens.product.ProductDetail
import com.info.shoppingapp.presentation.tiles.categories.DetailListTile
import com.info.shoppingapp.presentation.tiles.home.HomeCategory

@Composable
fun HomeView() {
    Scaffold(topBar = {
        SearchBar()
    }) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            HomeCategories()

            Discount()

            Subtitle(
                title = stringResource(id = R.string.new_items),
                style = TextStyle(
                    fontSize = 26.sp,
                    color = colors.primary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            NewItemsList()

        }
    }
}


@Composable
private fun SearchBar() {
    var text by remember { mutableStateOf(TextFieldValue(""))}
    val maxChar = 25
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(15.dp))
        ) {
            TextField(
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent),
                value = text,
                maxLines = 1,
                onValueChange = {
                    if (it.text.length <= maxChar) text = it
                },
                placeholder = {
                    Text(text = "Search", color = colors.primaryVariant)
                },
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(onClick = {
            context.startActivity(Intent(context, CategoriesPage::class.java))
        }) {
            Icon(
                imageVector = Icons.Default.FilterAlt,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
@Composable
fun HomeCategories() {
    val data = remember { CategoryFakeData.homeCategoryList }
    Box {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(data) {
                HomeCategory(category = it)
            }
        }
    }
}
@Composable
private fun Discount() {
    Surface(
        modifier = Modifier
            .padding(vertical = 25.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colors.surface)
            .fillMaxWidth()
            .height(110.dp)
            .clickable { }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Surface(
                    color = colors.onSurface,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Percent,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(24.dp),
                        tint = Color.Black
                    )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(end = 30.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.firstDiscountSentence),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = colors.primary
                    )
                    Text(
                        text = stringResource(id = R.string.secondDiscountSentence),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = colors.primary
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowRight,
                    contentDescription = null,
                    tint = colors.primary
                )

            }
        }
    }
}

private val repository: IProductRepository =
    ProductRepository(ProductRemoteDataSource(), ProductLocalDataSource(), ProductFakeDataSource())

@Composable
fun NewItemsList() {
//    val data = remember { repository.getProducts().subList(0, 2) }
    val context= LocalContext.current
    Scaffold()
    { padding ->
        val new = ProductFakeData.productList.subList(0,2)
        Box(modifier = Modifier.padding(padding)) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
            ) {
                items(new) {
                    DetailListTile(detail = it, onTap = { context.startActivity(ProductDetail.newIntent(context, it))
                        }
                    )
                }
            }
        }
    }
}