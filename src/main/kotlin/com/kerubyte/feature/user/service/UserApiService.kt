package com.kerubyte.feature.user.service

import com.kerubyte.feature.user.User

interface UserApiService {

    suspend fun insertUser(user: User): Boolean

    suspend fun findUserByEmail(email: String): User?
}