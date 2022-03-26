package com.kerubyte.feature.auth.request

data class LoginRequest(
    val email: String?,
    val password: String?
) {
    fun isValidRequest(): Boolean {
        return !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()
    }
}
