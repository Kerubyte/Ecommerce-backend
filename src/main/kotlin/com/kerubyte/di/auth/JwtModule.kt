package com.kerubyte.di.auth

import com.kerubyte.common.auth.AuthConfig
import org.koin.dsl.module

val jwtModule = module {

    single {
        AuthConfig()
    }
}