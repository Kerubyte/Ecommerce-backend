package com.kerubyte.feature.product.service

import com.kerubyte.common.database.DatabaseProvider
import com.kerubyte.common.database.DatabaseProviderImpl
import com.kerubyte.feature.product.Product
import org.litote.kmongo.Id
import org.litote.kmongo.findOneById
import org.litote.kmongo.`in`

class ProductApiServiceImpl(
    private val databaseProvider: DatabaseProvider
) : ProductApiService {
    override suspend fun getAllProducts(): List<Product> {
        return databaseProvider.provideProductCollection().find().toList()
    }

    override suspend fun getProductById(input: Id<Product>): Product? {
        return databaseProvider.provideProductCollection().findOneById(input)
    }

    override suspend fun searchProductsById(input: List<Id<Product>>): List<Product> {
        return databaseProvider.provideProductCollection().find(Product::_id `in` input).toList()
    }
}