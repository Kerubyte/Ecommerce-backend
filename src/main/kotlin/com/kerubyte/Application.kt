package com.kerubyte

import com.kerubyte.common.plugins.*
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureAuthentication()
    configureSerialization()
    configureDependencyInjection()
    configureRouting()
    configureMonitoring()
}
