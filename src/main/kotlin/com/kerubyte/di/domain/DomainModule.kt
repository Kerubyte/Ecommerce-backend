package com.kerubyte.di.domain

import com.kerubyte.common.domain.DomainProvider
import com.kerubyte.feature.auth.domain.LoginUserWithTokenUseCase
import com.kerubyte.feature.auth.domain.RegisterUserWithTokenUseCase
import com.kerubyte.feature.product.domain.GetProductByIdUseCase
import com.kerubyte.feature.product.domain.SearchProductsByIdUseCase
import com.kerubyte.feature.product.domain.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        DomainProvider(get(), get())
    }

    /** Product */
    single {
        GetProductsUseCase(get())
    }

    single {
        GetProductByIdUseCase(get())
    }

    single {
        SearchProductsByIdUseCase(get())
    }

    /** Auth */
    single {
        RegisterUserWithTokenUseCase(get())
    }

    single {
        LoginUserWithTokenUseCase(get())
    }
}