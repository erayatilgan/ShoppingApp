package com.info.shoppingapp.domain.repositories

import com.info.shoppingapp.domain.entities.Product

interface IProductRepository {
    fun getProducts() : List<Product>
}