package com.kerubyte.di.auth

import com.kerubyte.common.auth.Config
import org.koin.dsl.module

val jwtModule = module {

    single {
        Config()
    }
}