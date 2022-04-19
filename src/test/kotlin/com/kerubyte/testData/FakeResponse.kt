package com.kerubyte.testData

import com.kerubyte.common.util.RootResponse
import io.ktor.http.*

object FakeResponse {

    fun okSuccessResponse(): RootResponse<Any> {
        return RootResponse.SuccessResponse(
            HttpStatusCode.OK
        )
    }

    fun badRequestErrorResponse(): RootResponse<Any> {
        return RootResponse.ErrorResponse(
            HttpStatusCode.BadRequest
        )
    }
}