package com.info.shoppingapp.core.databases

import com.info.shoppingapp.R
import com.info.shoppingapp.domain.entities.Product


object ProductFakeData {
    private const val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus gravida massa laoreet ultrices porttitor."

    // Product Section
    private val product =
        Product(
            title = "Autumn Coat",
            price = 129.00f,
            description = text,
            image = R.drawable.autumn_coat,
            isLiked = true,
            sizies = listOf("XS", "S", "M", "L", "XL")
        )
    val productList = listOf(
        product, Product(
            title = "Light Dress",
            price = 160.00f,
            description = text,
            image = R.drawable.light_dress,
            isLiked = false,
            sizies = listOf("XS", "S", "M", "L", "XL")
        ),
        Product(
            title = "Classic Pants",
            price = 92.00f,
            description = text,
            image = R.drawable.classic_pants,
            isLiked = false,
            sizies = listOf("XS", "S", "M", "L", "XL")
        ),
        Product(
            title = "Chiffon Dress",
            price = 180.00f,
            description = text,
            image = R.drawable.chiffon_dress,
            isLiked = true,
            sizies = listOf("XS", "S", "M", "L", "XL")
        ),
        Product(
            title = "Jeans",
            price = 80.00f,
            description = text,
            image = R.drawable.jeans,
            isLiked = false,
            sizies = listOf("XS", "S", "M", "L", "XL")
        ),
        Product(
            title = "Basic T-Shirt",
            price = 50.00f,
            description = text,
            image = R.drawable.basit_tshirts,
            isLiked = true,
            sizies = listOf("XS", "S", "M", "L", "XL")
        )
    )
}