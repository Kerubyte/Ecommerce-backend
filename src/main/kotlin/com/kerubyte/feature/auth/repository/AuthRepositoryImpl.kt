package com.kerubyte.feature.auth.repository

import com.kerubyte.common.auth.AuthConfig
import com.kerubyte.common.util.RootResponse
import com.kerubyte.common.util.getHashFromString
import com.kerubyte.common.util.isPasswordHashMatch
import com.kerubyte.feature.auth.AuthConstants.INCORRECT_REQUEST
import com.kerubyte.feature.auth.AuthConstants.USER_CREATED
import com.kerubyte.feature.auth.AuthConstants.USER_EXISTS
import com.kerubyte.feature.auth.AuthConstants.USER_LOGGED_IN
import com.kerubyte.feature.auth.AuthConstants.WRONG_CREDENTIALS
import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.request.LoginRequest
import com.kerubyte.feature.user.User
import com.kerubyte.feature.user.service.UserApiService
import io.ktor.http.*
import org.litote.kmongo.toId

class AuthRepositoryImpl(
    private val jwtAuthConfig: AuthConfig,
    private val userApiService: UserApiService
) : AuthRepository {

    override suspend fun registerUserWithToken(input: AuthRequest?): RootResponse<Any> {
        return input?.let { authRequest ->
            if (!authRequest.isValidRequest()) {
                return RootResponse.ErrorResponse(
                    HttpStatusCode.BadRequest,
                    INCORRECT_REQUEST
                )
            }

            return when (checkIfUserExists(authRequest.email!!)) {
                true -> {
                    RootResponse.ErrorResponse(
                        HttpStatusCode.Conflict,
                        USER_EXISTS
                    )
                }
                false -> {
                    val userIdFromEmail = getHashFromString(authRequest.email).toId<User>()
                    //val hashFromPassword = getHashFromString(authRequest.password!!) TODO()
                    val user = User(
                        userIdFromEmail,
                        authRequest.firstName,
                        authRequest.lastName,
                        authRequest.email,
                        authRequest.password
                    )
                    val isAcknowledged = userApiService.insertUser(user)
                    when {
                        isAcknowledged -> {
                            val token = jwtAuthConfig.generateToken(user.id.toString())
                            RootResponse.SuccessResponse(
                                HttpStatusCode.Created,
                                token,
                                USER_CREATED
                            )
                        }
                        else ->
                            RootResponse.ErrorResponse(
                                HttpStatusCode.BadRequest,
                                INCORRECT_REQUEST
                            )
                    }
                }
            }
        } ?: RootResponse.ErrorResponse(
            HttpStatusCode.BadRequest,
            INCORRECT_REQUEST
        )
    }

    override suspend fun loginUserWithToken(input: LoginRequest?): RootResponse<Any> {
        input?.let { loginRequest ->
            if (!loginRequest.isValidRequest()) {
                return RootResponse.ErrorResponse(
                    HttpStatusCode.BadRequest,
                    INCORRECT_REQUEST
                )
            }

            val user = userApiService.findUserByEmail(loginRequest.email!!)
                ?: return RootResponse.ErrorResponse(
                    HttpStatusCode.Unauthorized,
                    WRONG_CREDENTIALS
                )
            val isPasswordMatch = isPasswordHashMatch(loginRequest.password!!, user.password!!)
            return when {
                isPasswordMatch -> {
                    val token = jwtAuthConfig.generateToken(user.id.toString())
                    RootResponse.SuccessResponse(
                        HttpStatusCode.OK,
                        token,
                        USER_LOGGED_IN
                    )
                }
                else -> {
                    RootResponse.ErrorResponse(
                        HttpStatusCode.Unauthorized,
                        WRONG_CREDENTIALS
                    )
                }
            }
        } ?: return RootResponse.ErrorResponse(
            HttpStatusCode.BadRequest,
            INCORRECT_REQUEST
        )
    }

    private suspend fun checkIfUserExists(email: String): Boolean {
        return userApiService.findUserByEmail(email) != null
    }
}