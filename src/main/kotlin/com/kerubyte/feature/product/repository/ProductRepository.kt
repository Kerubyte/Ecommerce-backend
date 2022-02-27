package com.kerubyte.feature.product.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.product.Product
import org.litote.kmongo.Id

interface ProductRepository {

    suspend fun getAllProducts(): RootResponse<Any>

    suspend fun getProductById(input: Id<Product>?): RootResponse<Any>

    suspend fun searchProductsById(input: List<Id<Product>>?): RootResponse<Any>
}