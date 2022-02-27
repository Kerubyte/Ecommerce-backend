package com.kerubyte.feature.product

import com.kerubyte.common.domain.DomainProvider
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.Id
import org.litote.kmongo.toId


fun Route.productsRoute(domainProvider: DomainProvider) {


    get<Products> {

        val response = domainProvider.provideGetProductsUseCase().invoke()
        call.respond(response.statusCode, response)
    }

    get<ProductById> { request ->

        val productId = request.id.toId<Product>()
        val response = domainProvider.provideGetProductByIdUseCase().invoke(productId)
        call.respond(response.statusCode, response)
    }

    post<SearchProductById> {

        val productIds = call.receiveOrNull<List<Id<Product>>>().orEmpty()
        val response = domainProvider.provideSearchProductsByIdUseCase().invoke(productIds)
        call.respond(response.statusCode, response)
    }
}