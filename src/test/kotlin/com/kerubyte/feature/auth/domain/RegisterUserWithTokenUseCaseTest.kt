package com.kerubyte.feature.auth.domain

import com.google.common.truth.Truth.assertThat
import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.auth.repository.AuthRepository
import com.kerubyte.testData.FakeRequest.fakeInvalidAuthRequest
import com.kerubyte.testData.FakeRequest.fakeValidAuthRequest
import com.kerubyte.testData.FakeResponse.badRequestErrorResponse
import com.kerubyte.testData.FakeResponse.okSuccessResponse
import com.kerubyte.testUtil.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class RegisterUserWithTokenUseCaseTest {

    @get:Rule
    var coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private val mockAuthRepository = mockk<AuthRepository>()

    private val registerUserWithTokenUseCase = RegisterUserWithTokenUseCase(mockAuthRepository)

    @Test
    fun `given valid data returns Success`() {
        val validRequest = fakeValidAuthRequest()
        coEvery { mockAuthRepository.registerUserWithToken(validRequest) } returns okSuccessResponse()

        runBlocking {
            val result = registerUserWithTokenUseCase.invoke(validRequest)
            assertThat(result is RootResponse.SuccessResponse)
        }

        coVerify { mockAuthRepository.registerUserWithToken(validRequest) }
    }

    @Test
    fun `given not valid data returns Error`() {
        val invalidRequest = fakeInvalidAuthRequest()

        coEvery { mockAuthRepository.registerUserWithToken(invalidRequest) } returns badRequestErrorResponse()

        runBlocking {
            val result = registerUserWithTokenUseCase.invoke(invalidRequest)
            assertThat(result is RootResponse.ErrorResponse)
        }

        coVerify { mockAuthRepository.registerUserWithToken(invalidRequest) }

    }
}