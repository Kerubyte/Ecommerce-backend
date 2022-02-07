package com.kerubyte

import com.kerubyte.common.plugins.configureMonitoring
import com.kerubyte.common.plugins.configureRouting
import com.kerubyte.common.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureMonitoring()
}
