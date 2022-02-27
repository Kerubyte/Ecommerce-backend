package com.kerubyte.feature.product.service

import com.kerubyte.feature.product.Product
import org.litote.kmongo.Id

interface ProductApiService {

    suspend fun getAllProducts(): List<Product>

    suspend fun getProductById(input: Id<Product>): Product?

    suspend fun searchProductsById(input: List<Id<Product>>): List<Product>
}