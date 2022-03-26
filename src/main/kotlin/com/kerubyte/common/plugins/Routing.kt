package com.kerubyte.common.plugins

import com.kerubyte.common.domain.DomainProvider
import com.kerubyte.feature.auth.authRoute
import com.kerubyte.feature.product.productsRoute
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {

    val domainProvider by inject<DomainProvider>()

    install(Locations)

    routing {
        productsRoute(domainProvider)
        authRoute(domainProvider)
    }
}
