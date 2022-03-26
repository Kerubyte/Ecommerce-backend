package com.kerubyte.feature.auth.domain

import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.RootUseCase
import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.feature.auth.request.LoginRequest

class LoginUserWithTokenUseCase(
    private val authRepository: AuthRepository
) : RootUseCase<LoginRequest, Any> {
    override suspend fun invoke(input: LoginRequest?): RootResponse<Any> {
        return authRepository.loginUserWithToken(input)
    }
}