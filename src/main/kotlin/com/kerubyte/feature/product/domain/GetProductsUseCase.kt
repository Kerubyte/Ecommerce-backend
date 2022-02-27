package com.kerubyte.feature.product.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.product.repository.ProductRepository

class GetProductsUseCase(
    private val productRepository: ProductRepository
) : RootUseCase<String, Any> {

    override suspend fun invoke(input: String?): RootResponse<Any> {
        return productRepository.getAllProducts()
    }
}