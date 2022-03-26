package com.kerubyte.feature.auth.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.request.LoginRequest

interface AuthRepository {

    suspend fun registerUserWithToken(input: AuthRequest?): RootResponse<Any>

    suspend fun loginUserWithToken(input: LoginRequest?): RootResponse<Any>
}