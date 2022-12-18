package com.info.shoppingapp.domain.entities

import java.io.Serializable

open class Product(
    val title: String,
    val price: Float,
    val description: String,
    val image: Int,
    val isLiked: Boolean,
    val sizies: List<String>
): Serializable