package com.kerubyte.testData

import com.kerubyte.feature.auth.request.AuthRequest
import com.kerubyte.feature.auth.request.LoginRequest

object FakeRequest {

    fun fakeValidAuthRequest(): AuthRequest {
        return AuthRequest(
            "Majek",
            "Fajek",
            "test@example.com",
            "password"
        )
    }

    fun fakeInvalidAuthRequest(): AuthRequest {
        return AuthRequest(
            "",
            "",
            "email@example.com",
            "password"
        )
    }

    fun fakeValidLoginRequest(): LoginRequest {
        return LoginRequest(
            "test@example.com",
            "password"
        )
    }

    fun fakeInvalidLoginRequest(): LoginRequest {
        return LoginRequest(
            "",
            "password"
        )
    }

    fun fakeInvalidEmailLoginRequest(): LoginRequest {
        return LoginRequest(
            "wrong",
            "password"
        )
    }

    fun fakeInvalidPasswordLoginRequest(): LoginRequest {
        return LoginRequest(
            "test@example.com",
            "wrong"
        )
    }
}