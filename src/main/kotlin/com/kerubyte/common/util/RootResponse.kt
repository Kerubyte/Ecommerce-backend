package com.kerubyte.common.util

import io.ktor.http.*

sealed class RootResponse<T : Any>(
    val statusCode: HttpStatusCode,
    val data: T? = null,
    val message: String? = null
) {

    class SuccessResponse<T : Any>(
        statusCode: HttpStatusCode,
        data: T? = null,
        message: String? = null
    ) : RootResponse<T>(statusCode, data, message)

    class ErrorResponse<T : Any>(
        statusCode: HttpStatusCode,
        message: String? = null
    ) : RootResponse<T>(statusCode, message = message)
}
