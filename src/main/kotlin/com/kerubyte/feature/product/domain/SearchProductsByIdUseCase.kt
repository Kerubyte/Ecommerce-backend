package com.kerubyte.feature.product.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.repository.ProductRepository
import org.litote.kmongo.Id

class SearchProductsByIdUseCase(
    private val productRepository: ProductRepository
) : RootUseCase<List<Id<Product>>, Any> {

    override suspend fun invoke(input: List<Id<Product>>?): RootResponse<Any> {
        return productRepository.searchProductsById(input)
    }
}