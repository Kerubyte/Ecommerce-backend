package com.kerubyte.feature.auth.domain

import com.google.common.truth.Truth
import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.testData.FakeRequest.fakeInvalidLoginRequest
import com.kerubyte.testData.FakeRequest.fakeValidLoginRequest
import com.kerubyte.testData.FakeResponse
import com.kerubyte.testUtil.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class LoginUserWithTokenUseCaseTest {

    @get:Rule
    var coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private val mockAuthRepository = mockk<AuthRepository>()

    private val loginUserWithTokenUseCase = LoginUserWithTokenUseCase(mockAuthRepository)

    @Test
    fun `given valid data returns Success`() {
        val validRequest = fakeValidLoginRequest()
        coEvery { mockAuthRepository.loginUserWithToken(validRequest) } returns FakeResponse.okSuccessResponse()

        runBlocking {
            val result = loginUserWithTokenUseCase.invoke(validRequest)
            Truth.assertThat(result is RootResponse.SuccessResponse)
        }

        coVerify { mockAuthRepository.loginUserWithToken(validRequest) }
    }

    @Test
    fun `given not valid data returns Error`() {
        val invalidRequest = fakeInvalidLoginRequest()

        coEvery { mockAuthRepository.loginUserWithToken(invalidRequest) } returns FakeResponse.badRequestErrorResponse()

        runBlocking {
            val result = loginUserWithTokenUseCase.invoke(invalidRequest)
            Truth.assertThat(result is RootResponse.ErrorResponse)
        }

        coVerify { mockAuthRepository.loginUserWithToken(invalidRequest) }

    }
}