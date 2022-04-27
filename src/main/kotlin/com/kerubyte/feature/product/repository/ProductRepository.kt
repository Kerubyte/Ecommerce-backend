package com.kerubyte.feature.product.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.SearchRequest
import io.ktor.http.*
import org.litote.kmongo.Id

interface ProductRepository {

    suspend fun getAllProducts(): Pair<HttpStatusCode, RootResponse<Any>>

    suspend fun getProductById(input: Id<Product>?): Pair<HttpStatusCode, RootResponse<Any>>

    suspend fun searchProductsById(input: SearchRequest?): Pair<HttpStatusCode, RootResponse<Any>>
}