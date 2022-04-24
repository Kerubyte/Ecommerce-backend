package com.kerubyte.common.util

sealed class RootResponse<T : Any>(
    val message: String? = null,
    val data: T? = null
) {

    class SuccessResponse<T : Any>(
        message: String? = null,
        data: T? = null
    ) : RootResponse<T>(message, data)

    class ErrorResponse<T : Any>(
        message: String? = null
    ) : RootResponse<T>(message = message)
}
