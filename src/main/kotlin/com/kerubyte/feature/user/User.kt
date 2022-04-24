package com.kerubyte.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class User(
    @BsonId
    val id: Id<User>,
    val firstName: String,
    val lastName: String,
    val email: String,
    val passwordHash: String
) {
    fun asDTO(): UserDTO {
        return UserDTO(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email
        )
    }
}

data class UserDTO(
    @BsonId
    val id: Id<User>,
    val firstName: String,
    val lastName: String,
    val email: String,
)
