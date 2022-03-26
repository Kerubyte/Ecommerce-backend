package com.kerubyte.di.service

import com.kerubyte.feature.product.service.ProductApiService
import com.kerubyte.feature.product.service.ProductApiServiceImpl
import com.kerubyte.feature.user.User
import com.kerubyte.feature.user.service.UserApiService
import com.kerubyte.feature.user.service.UserApiServiceImpl
import org.koin.dsl.module

val serviceModule = module {

    single<ProductApiService> {
        ProductApiServiceImpl(get())
    }

    single<UserApiService> {
        UserApiServiceImpl(get())
    }
}