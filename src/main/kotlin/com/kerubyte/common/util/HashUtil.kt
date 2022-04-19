package com.kerubyte.common.util

import java.security.MessageDigest


fun getHashFromString(stringToHash: String): String {
    val bytes = MessageDigest.getInstance("SHA-1").digest(stringToHash.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}

fun isPasswordHashMatch(password: String, passwordHash: String): Boolean {
    val newPasswordHash = getHashFromString(password)
    return newPasswordHash == passwordHash
}
