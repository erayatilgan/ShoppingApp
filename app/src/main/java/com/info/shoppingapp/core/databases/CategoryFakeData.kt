package com.info.shoppingapp.core.databases

import com.info.shoppingapp.R
import com.info.shoppingapp.domain.entities.Category

object CategoryFakeData {

    private val category =
        Category(
            category = "All",
            image = R.drawable.grid
        )
    val homeCategoryList = listOf(
        category,
        Category(
            category = "Clothing",
            image = R.drawable.dress
        ),
        Category(
            category = "Accessories",
            image = R.drawable.glasses
        ),
        Category(
            category = "Bags",
            image = R.drawable.handbag
        ),
        Category(
            category = "Footwear",
            image = R.drawable.footwear
        ),
        Category(
            category = "Sports",
            image = R.drawable.sport
        )
    )


    val categoryList = listOf(
        Category(
            category = "Clothing",
            image = R.drawable.dress
        ),
        Category(
            category = "Accessories",
            image = R.drawable.glasses
        ),
        Category(
            category = "Bags",
            image = R.drawable.handbag
        ),
        Category(
            category = "Footwear",
            image = R.drawable.footwear
        ),
        Category(
            category = "Sports",
            image = R.drawable.sport
        )
    )
}