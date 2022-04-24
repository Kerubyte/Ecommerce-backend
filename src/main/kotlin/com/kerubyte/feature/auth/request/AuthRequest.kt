package com.kerubyte.feature.auth.request

data class AuthRequest(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
) {
    fun validateRequest(): ValidatedAuthRequest? {
        return if (!firstName.isNullOrEmpty() &&
            !lastName.isNullOrEmpty() &&
            !email.isNullOrEmpty() &&
            !password.isNullOrEmpty()
        ) {
            ValidatedAuthRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password
            )
        } else {
            null
        }
    }
}

data class ValidatedAuthRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)