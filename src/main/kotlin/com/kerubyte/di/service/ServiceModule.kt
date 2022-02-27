package com.kerubyte.di.service

import com.kerubyte.feature.product.service.ProductApiService
import com.kerubyte.feature.product.service.ProductApiServiceImpl
import org.koin.dsl.module

val serviceModule = module {

    single<ProductApiService> {
        ProductApiServiceImpl(get())
    }
}