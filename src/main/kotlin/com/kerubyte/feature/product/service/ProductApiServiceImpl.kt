package com.kerubyte.feature.product.service

import com.kerubyte.feature.product.Product
import org.litote.kmongo.Id
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineCollection

class ProductApiServiceImpl(
    private val productsCollection: CoroutineCollection<Product>
) : ProductApiService {

    override suspend fun getAllProducts(): List<Product> {
        return productsCollection.find().toList()
    }

    override suspend fun getProductById(input: Id<Product>): Product? {
        return productsCollection.findOneById(input)
    }

    override suspend fun searchProductsById(input: List<Id<Product>>): List<Product> {
        return productsCollection.find(Product::_id `in` input).toList()
    }
}