package com.kerubyte.feature.auth

import com.kerubyte.common.domain.DomainProvider
import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.request.LoginRequest
import io.ktor.application.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authRoute(domainProvider: DomainProvider) {

    post<RegisterUser> {
        val authRequest = call.receive<AuthRequest>()
        val response = domainProvider.provideRegisterUserWithTokenUseCase().invoke(authRequest)
        call.respond(response.statusCode, response)
    }

    post<LoginUser> {
        val loginRequest = call.receive<LoginRequest>()
        val response = domainProvider.provideLoginUserWithTokenUseCase().invoke(loginRequest)
        call.respond(response.statusCode, response)
    }
}