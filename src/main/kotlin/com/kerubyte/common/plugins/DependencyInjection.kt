package com.kerubyte.common.plugins

import com.kerubyte.di.auth.jwtModule
import com.kerubyte.di.database.databaseModule
import com.kerubyte.di.domain.domainModule
import com.kerubyte.di.repository.repositoryModule
import com.kerubyte.di.service.serviceModule
import io.ktor.application.*
import org.koin.ktor.ext.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(
            databaseModule,
            jwtModule,
            serviceModule,
            repositoryModule,
            domainModule
        )
    }
}