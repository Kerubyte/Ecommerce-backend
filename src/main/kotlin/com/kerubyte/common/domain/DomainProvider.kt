package com.kerubyte.common.domain

import com.kerubyte.feature.auth.domain.LoginUserWithTokenUseCase
import com.kerubyte.feature.auth.domain.RegisterUserWithTokenUseCase
import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.feature.product.domain.GetProductByIdUseCase
import com.kerubyte.feature.product.domain.GetProductsUseCase
import com.kerubyte.feature.product.domain.SearchProductsByIdUseCase
import com.kerubyte.feature.product.repository.ProductRepository

class DomainProvider(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) {

    /** Products Use Cases */
    fun provideGetProductsUseCase(): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    fun provideGetProductByIdUseCase(): GetProductByIdUseCase {
        return GetProductByIdUseCase(productRepository)
    }

    fun provideSearchProductsByIdUseCase(): SearchProductsByIdUseCase {
        return SearchProductsByIdUseCase(productRepository)
    }

    /** Auth Use Cases */
    fun provideRegisterUserWithTokenUseCase(): RegisterUserWithTokenUseCase {
        return RegisterUserWithTokenUseCase(authRepository)
    }

    fun provideLoginUserWithTokenUseCase(): LoginUserWithTokenUseCase {
        return LoginUserWithTokenUseCase(authRepository)
    }
}