package com.info.shoppingapp.infrastructure.data_sources.product

import com.info.shoppingapp.R
import com.info.shoppingapp.domain.entities.Product

class ProductFakeDataSource {
    fun getProducts(): List<Product> {
        return List(size = 2) {
            Product(
                title = "Product $it",
                price = 160.00f,
                description = "description",
                image = R.drawable.light_dress,
                isLiked = it % 2 == 0,
                sizies = listOf("XS", "S", "M", "L", "XL")
            )
            Product(
                title = "Product $it",
                price = 160.00f,
                description = "description",
                image = R.drawable.autumn_coat,
                isLiked = it % 2 == 0,
                sizies = listOf("XS", "S", "M", "L", "XL")
            )
        }
    }
}