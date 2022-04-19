package com.kerubyte.testData

import com.kerubyte.feature.user.User
import org.litote.kmongo.toId

object FakeUser {

    fun fakeValidUser(): User {
        return User(
            "567159d622ffbb50b11b0efd307be358624a26ee".toId(),
            "Majek",
            "Fajek",
            "test@example.com",
            "password"
        )
    }

    fun fakeValidToken(): String {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0ZXN0LWJhY2tlbmQiLCJ1c2VyQXV0aCI6IjU2NzE1OWQ2MjJmZmJiNTBiMTFiMGVmZDMwN2JlMzU4NjI0YTI2ZWUiLCJpc3MiOiJ0ZXN0LWJhY2tlbmQifQ.F9SG7LNJqbQBGmR7PD6XMg8guzHEzpl18YFl_l_WWRc"
    }
}