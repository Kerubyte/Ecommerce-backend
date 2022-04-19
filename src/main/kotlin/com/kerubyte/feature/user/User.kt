package com.kerubyte.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class User(
    @BsonId
    val id: Id<User>,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null
) {
    fun asResponse(): User {
        return User(
            id,
            firstName,
            lastName,
            email,
            null
        )
    }
}
