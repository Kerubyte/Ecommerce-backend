package com.kerubyte.feature.auth.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.feature.auth.request.LoginRequest
import io.ktor.http.*

class LoginUserWithTokenUseCase(
    private val authRepository: AuthRepository
) : RootUseCase<LoginRequest, Any> {
    override suspend fun invoke(input: LoginRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        return authRepository.loginUserWithToken(input)
    }
}