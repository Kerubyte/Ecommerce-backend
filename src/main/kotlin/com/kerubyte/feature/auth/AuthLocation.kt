package com.kerubyte.feature.auth

import com.kerubyte.feature.auth.AuthConstants.LOGIN
import com.kerubyte.feature.auth.AuthConstants.REGISTER
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(REGISTER)
class RegisterUser

@KtorExperimentalLocationsAPI
@Location(LOGIN)
class LoginUser