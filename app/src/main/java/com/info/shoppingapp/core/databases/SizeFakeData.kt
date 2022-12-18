package com.info.shoppingapp.core.databases

import java.io.Serializable

data class SizeX(
    val size: String,
): Serializable

object SizeData {
    val sizeList = listOf(
        SizeX(
            size = "XS"
        ),
        SizeX(
            size = "S"
        ),
        SizeX(
            size = "M"
        ),
        SizeX(
            size = "L"
        ),
        SizeX(
            size = "XL"
        ),
        SizeX(
            size = "XXL"
        ),
    )
}

