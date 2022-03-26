package com.kerubyte.feature.auth

object AuthConstants {

    const val REGISTER = "/api/users/register"
    const val LOGIN = "/api/users/login"

    /**
     * Constants for AuthRepositoryImpl
     */
    const val USER_CREATED = "User created"
    const val USER_LOGGED_IN = "Logged In"
    const val USER_EXISTS = "User already exists"
    const val USER_NOT_FOUND = "User not found"
    const val INCORRECT_REQUEST = "Incorrect request body"
    const val WRONG_CREDENTIALS = "Incorrect email or password"

}