package com.kerubyte.feature.product.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.repository.ProductRepository
import io.ktor.http.*
import org.litote.kmongo.Id

class GetProductByIdUseCase(
    private val productRepository: ProductRepository
) : RootUseCase<Id<Product>, Any> {

    override suspend fun invoke(input: Id<Product>?): Pair<HttpStatusCode, RootResponse<Any>> {
        return productRepository.getProductById(input)
    }
}