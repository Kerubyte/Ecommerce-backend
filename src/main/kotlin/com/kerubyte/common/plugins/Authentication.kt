package com.kerubyte.common.plugins

import com.kerubyte.common.auth.AuthConfig
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt {
            AuthConfig.initialize()
            verifier(AuthConfig.instance.verifier)
            validate { jwtCredential ->
                val payload = jwtCredential.payload
                if (payload.getClaim(AuthConfig.JWTCLAIM).asString() != "") {
                    JWTPrincipal(payload)
                } else {
                    null
                }
            }
        }

    }

}