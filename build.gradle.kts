val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val kmongo_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "com.kerubyte"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("com.google.truth:truth:1.0.1")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.4.5")

    // Koin for Ktor
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    // KMongo
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    testImplementation("org.litote.kmongo:kmongo-flapdoodle:4.5.1")
    testImplementation("org.litote.kmongo:kmongo-core-tests:4.5.1")

    // Jwt
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
}