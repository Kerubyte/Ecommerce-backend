package com.kerubyte.common.plugins

import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import org.litote.kmongo.Id
import org.litote.kmongo.toId

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(
                Id::class.java,
                JsonSerializer<Id<Any>> { id, _, _ ->
                    JsonPrimitive(id?.toString())
                })
            registerTypeAdapter(Id::class.java,
                JsonDeserializer<Id<Any>> { id, _, _ ->
                    id.asString.toId()
                })
        }
    }
}
