package com.kerubyte.di.repository

import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.feature.auth.repository.AuthRepositoryImpl
import com.kerubyte.feature.product.repository.ProductRepository
import com.kerubyte.feature.product.repository.ProductRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}