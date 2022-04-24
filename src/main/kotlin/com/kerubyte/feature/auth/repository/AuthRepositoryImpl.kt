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
    override suspend fun registerUserWithToken(input: AuthRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        return input?.let { authRequest ->

            authRequest.validateRequest()?.let { (firstName, lastName, email, password) ->

                return when (checkIfUserExists(email)) {
                    true -> {
                        Pair(
                            HttpStatusCode.Conflict,
                            RootResponse.ErrorResponse(
                                message = USER_EXISTS
                            )
                        )
                    }
                    false -> {
                        val user = User(
                            id = getHashFromString(email).toId(),
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            passwordHash = getHashFromString(password)
                        )

                        val isAcknowledged = userApiService.insertUser(user)
                        when {
                            isAcknowledged -> {
                                val token = jwtAuthConfig.generateToken(user.id.toString())
                                Pair(
                                    HttpStatusCode.Created,
                                    RootResponse.SuccessResponse(
                                        message = USER_CREATED,
                                        data = token
                                    )
                                )
                            }
                            else -> Pair(
                                HttpStatusCode.BadRequest,
                                RootResponse.ErrorResponse(
                                    message = INCORRECT_REQUEST
                                )
                            )
                        }
                    }
                }
            } ?: Pair(
                HttpStatusCode.BadRequest,
                RootResponse.ErrorResponse(
                    message = INCORRECT_REQUEST
                )
            )
        } ?: Pair(
            HttpStatusCode.BadRequest,
            RootResponse.ErrorResponse(
                message = INCORRECT_REQUEST
            )
        )
    }

    override suspend fun loginUserWithToken(input: LoginRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        return input?.let { loginRequest ->

            loginRequest.validateRequest()?.let { (email, password) ->

                val user = userApiService.findUserByEmail(email) ?: return Pair(
                    HttpStatusCode.Unauthorized,
                    RootResponse.ErrorResponse(
                        message = WRONG_CREDENTIALS
                    )
                )
                val isPasswordMatch = isPasswordHashMatch(password, user.passwordHash)
                return when {
                    isPasswordMatch -> {
                        val token = jwtAuthConfig.generateToken(user.id.toString())
                        Pair(
                            HttpStatusCode.OK,
                            RootResponse.SuccessResponse(
                                message = USER_LOGGED_IN,
                                data = token
                            )
                        )
                    }
                    else -> {
                        Pair(
                            HttpStatusCode.Unauthorized,
                            RootResponse.ErrorResponse(
                                message = WRONG_CREDENTIALS
                            )
                        )
                    }
                }
            } ?: Pair(
                HttpStatusCode.BadRequest,
                RootResponse.ErrorResponse(
                    message = INCORRECT_REQUEST
                )
            )
        } ?: return Pair(
            HttpStatusCode.BadRequest,
            RootResponse.ErrorResponse(
                message = INCORRECT_REQUEST
            )
        )
    }

    private suspend fun checkIfUserExists(email: String): Boolean {
        return userApiService.findUserByEmail(email) != null
    }
}