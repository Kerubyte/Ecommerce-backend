package com.kerubyte.feature.auth.request

data class LoginRequest(
    val email: String?,
    val password: String?
) {
    fun validateRequest(): ValidatedLoginRequest? {
        return if (!email.isNullOrBlank() &&
            !password.isNullOrBlank()
        ) {
            ValidatedLoginRequest(
                email = email,
                password = password
            )
        } else {
            null
        }
    }
}

data class ValidatedLoginRequest(
    val email: String,
    val password: String
)
