package com.kerubyte.feature.user.service

import com.kerubyte.common.database.DatabaseProvider
import com.kerubyte.feature.user.User
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

class UserApiServiceImpl(
    private val databaseProvider: DatabaseProvider
) : UserApiService {

    override suspend fun insertUser(user: User): Boolean {
        return databaseProvider.provideUserCollection().insertOne(user).wasAcknowledged()
    }

    override suspend fun findUserByEmail(email: String): User? {
        return databaseProvider.provideUserCollection().findOne(User::email eq email)
    }
}