package com.kerubyte.common.plugins

import com.kerubyte.common.auth.Config
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt {
            verifier(Config.instance.verifier)
            validate { jwtCredential ->
                val payload = jwtCredential.payload
                if (payload.getClaim(Config.JWTCLAIM).asString() != "") {
                    JWTPrincipal(payload)
                } else {
                    null
                }
            }
        }

    }

}