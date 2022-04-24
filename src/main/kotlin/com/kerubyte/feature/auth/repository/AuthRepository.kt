package com.kerubyte.feature.auth.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.request.LoginRequest
import io.ktor.http.*

interface AuthRepository {

    suspend fun registerUserWithToken(input: AuthRequest?): Pair<HttpStatusCode, RootResponse<Any>>

    suspend fun loginUserWithToken(input: LoginRequest?): Pair<HttpStatusCode, RootResponse<Any>>
}