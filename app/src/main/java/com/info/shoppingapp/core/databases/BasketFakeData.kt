package com.info.shoppingapp.core.databases

import com.info.shoppingapp.R
import com.info.shoppingapp.domain.entities.BasketProduct
import com.info.shoppingapp.domain.entities.Product

object BasketFakeData {
    private const val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus gravida massa laoreet ultrices porttitor."


    val itemsList = listOf(
        BasketProduct(
            amount = 1,
            Product(
                title = "White Shirt",
                price = 72.00f,
                image = R.drawable.autumn_coat,
                isLiked = false,
                description = text,
                sizies = listOf("XS", "S", "M", "L", "XL")
            ),
        ),
        BasketProduct(
            amount = 1, Product(
                title = "Stylish Pantsuit",
                price = 87.00f,
                image = R.drawable.jeans,
                isLiked = true,
                description = text,
                sizies = listOf("XS", "S", "M", "L", "XL")
            )
        )

    )
}