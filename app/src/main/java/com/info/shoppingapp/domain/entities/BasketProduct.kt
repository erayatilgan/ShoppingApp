package com.info.shoppingapp.domain.entities

import java.io.Serializable

open class BasketProduct(
    var amount: Int,
    var product:Product
): Serializable