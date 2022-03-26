package com.kerubyte.feature.auth.request

data class AuthRequest(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
) {
    fun isValidRequest(): Boolean {
        return !firstName.isNullOrEmpty() &&
                !lastName.isNullOrEmpty() &&
                !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()
    }
}