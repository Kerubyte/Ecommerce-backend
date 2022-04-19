package com.kerubyte.feature.auth.repository

import com.google.common.truth.Truth.assertThat
import com.kerubyte.common.auth.AuthConfig
import com.kerubyte.common.database.DatabaseProviderImpl
import com.kerubyte.feature.user.service.UserApiServiceImpl
import com.kerubyte.testData.FakeRequest.fakeInvalidEmailLoginRequest
import com.kerubyte.testData.FakeRequest.fakeInvalidLoginRequest
import com.kerubyte.testData.FakeRequest.fakeInvalidPasswordLoginRequest
import com.kerubyte.testData.FakeRequest.fakeValidAuthRequest
import com.kerubyte.testData.FakeRequest.fakeValidLoginRequest
import com.kerubyte.testData.FakeUser.fakeValidUser
import com.kerubyte.testUtil.FlapdoodleRule
import com.kerubyte.testUtil.MainCoroutineRule
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.bson.Document
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    @get:Rule
    val rule: FlapdoodleRule = FlapdoodleRule()

    @get:Rule
    var coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private val database by lazy {
        rule.database
    }

    private val dbProvider = DatabaseProviderImpl(database)
    private val authConfig = AuthConfig()
    private val userApiService = UserApiServiceImpl(dbProvider)
    private val authRepository = AuthRepositoryImpl(authConfig, userApiService)

    @Nested
    inner class RegisterUserWithToken {
        @Test
        fun `given valid register data returns 201`() {
            val validAuthRequest = fakeValidAuthRequest()

            runBlocking {
                val response = authRepository.registerUserWithToken(validAuthRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.Created)
            }
        }

        @Test
        fun `given null register data returns 400`() {
            val nullData = null

            runBlocking {
                val response = authRepository.registerUserWithToken(nullData)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.BadRequest)
            }
        }

        @Test
        fun `given existing user returns 409`() {
            val validAuthRequest = fakeValidAuthRequest()


            runBlocking {
                authRepository.registerUserWithToken(validAuthRequest)
                val response = authRepository.registerUserWithToken(validAuthRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.Conflict)
            }
        }
    }

    @Nested
    inner class LoginUserWithToken {
        @Test
        fun `given valid login data returns OK`() {
            val validLoginRequest = fakeValidLoginRequest()
            val validUser = fakeValidUser()

            rule.populateDB(
                "user",
                Document()
                    .append("id", validUser.id)
                    .append("email", validUser.email)
                    .append("password", validUser.password)

            )

            runBlocking {
                val response = authRepository.loginUserWithToken(validLoginRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.OK)
            }
        }

        @Test
        fun `given null login data returns 400`() {
            val nullData = null

            runBlocking {
                val response = authRepository.loginUserWithToken(nullData)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.BadRequest)
            }
        }

        @Test
        fun `given invalid login request returns 400`() {
            val invalidRequest = fakeInvalidLoginRequest()

            runBlocking {
                val response = authRepository.loginUserWithToken(invalidRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.BadRequest)
            }
        }

        @Test
        fun `given invalid login email returns 401`() {
            val invalidEmailRequest = fakeInvalidEmailLoginRequest()

            runBlocking {
                val response = authRepository.loginUserWithToken(invalidEmailRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.Unauthorized)
            }
        }

        @Test
        fun `given invalid login password returns 401`() {
            val invalidPasswordRequest = fakeInvalidPasswordLoginRequest()


            runBlocking {
                val response = authRepository.loginUserWithToken(invalidPasswordRequest)
                assertThat(response.statusCode).isEqualTo(HttpStatusCode.Unauthorized)
            }
        }
    }
}