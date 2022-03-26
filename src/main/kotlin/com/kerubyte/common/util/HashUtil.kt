package com.kerubyte.common.util

import java.security.MessageDigest

fun getHashFromString(stringToHash: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    return messageDigest.digest(stringToHash.toByteArray()).toString()
}

fun isPasswordHashMatch(password: String, passwordHash: String): Boolean {
    return password == passwordHash
}