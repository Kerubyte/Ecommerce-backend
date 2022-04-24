package com.kerubyte.common.util

import io.ktor.http.*

interface RootUseCase<in I, O : Any> {

    suspend operator fun invoke(input: I? = null): Pair<HttpStatusCode, RootResponse<O>>
}