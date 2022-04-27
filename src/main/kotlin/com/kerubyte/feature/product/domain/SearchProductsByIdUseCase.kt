package com.kerubyte.feature.product.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.SearchRequest
import com.kerubyte.feature.product.repository.ProductRepository
import io.ktor.http.*
import org.litote.kmongo.Id

class SearchProductsByIdUseCase(
    private val productRepository: ProductRepository
) : RootUseCase<SearchRequest, Any> {

    override suspend fun invoke(input: SearchRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        return productRepository.searchProductsById(input)
    }
}