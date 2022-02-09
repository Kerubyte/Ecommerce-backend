package com.kerubyte.common.util

import io.ktor.http.*

interface RootResponse<T : Any>

data class SuccessResponse<T : Any>(
    val statusCode: HttpStatusCode,
    val data: T? = null,
    val message: String? = null
) : RootResponse<T>

data class ErrorResponse<T : Any>(
    val statusCode: HttpStatusCode,
    val message: String? = null
) : RootResponse<T>
