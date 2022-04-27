package com.kerubyte.feature.product.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.product.repository.ProductRepository
import io.ktor.http.*

class GetProductsUseCase(
    private val productRepository: ProductRepository
) : RootUseCase<String, Any> {

    override suspend fun invoke(input: String?): Pair<HttpStatusCode, RootResponse<Any>> {
        return productRepository.getAllProducts()
    }
}