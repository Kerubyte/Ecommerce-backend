package com.kerubyte.feature.auth.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.repository.AuthRepository
import io.ktor.http.*

class RegisterUserWithTokenUseCase(
    private val authRepository: AuthRepository
) : RootUseCase<AuthRequest, Any> {
    override suspend fun invoke(input: AuthRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        return authRepository.registerUserWithToken(input)
    }


}