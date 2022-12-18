package com.info.shoppingapp.infrastructure.repositories

import com.info.shoppingapp.core.constants.hasConnection
import com.info.shoppingapp.core.constants.useFakeData
import com.info.shoppingapp.domain.entities.Product
import com.info.shoppingapp.domain.repositories.IProductRepository
import com.info.shoppingapp.infrastructure.data_sources.product.ProductFakeDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductLocalDataSource
import com.info.shoppingapp.infrastructure.data_sources.product.ProductRemoteDataSource

class ProductRepository(private val remoteDataSource: ProductRemoteDataSource, private val localDataSource: ProductLocalDataSource, private val fakeDataSource: ProductFakeDataSource) : IProductRepository {
    override fun getProducts(): List<Product> {
        if(useFakeData) return fakeDataSource.getProducts()

        if(hasConnection) remoteDataSource.fetchProducts()

        return localDataSource.getProducts()
    }
}