package com.kerubyte.feature.auth.request

import com.kerubyte.common.util.isValidEmail
import com.kerubyte.common.util.isValidPassword

data class AuthRequest(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
) {
    fun validateRequest(): ValidatedAuthRequest? {
        return if (!firstName.isNullOrBlank() &&
            !lastName.isNullOrBlank() &&
            email.isValidEmail() &&
            password.isValidPassword()
        ) {
            ValidatedAuthRequest(
                firstName = firstName,
                lastName = lastName,
                email = email!!,
                password = password!!
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