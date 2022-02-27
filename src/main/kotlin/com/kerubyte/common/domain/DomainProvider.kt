package com.kerubyte.common.domain

import com.kerubyte.feature.product.domain.GetProductByIdUseCase
import com.kerubyte.feature.product.domain.SearchProductsByIdUseCase
import com.kerubyte.feature.product.domain.GetProductsUseCase
import com.kerubyte.feature.product.repository.ProductRepository

class DomainProvider(private val productRepository: ProductRepository) {

    fun provideGetProductsUseCase(): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    fun provideGetProductByIdUseCase(): GetProductByIdUseCase {
        return GetProductByIdUseCase(productRepository)
    }

    fun provideSearchProductsByIdUseCase(): SearchProductsByIdUseCase {
        return SearchProductsByIdUseCase(productRepository)
    }
}