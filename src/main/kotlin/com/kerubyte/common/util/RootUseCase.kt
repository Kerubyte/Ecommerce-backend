package com.kerubyte.common.util

interface RootUseCase<in I, O : Any> {

    suspend operator fun invoke(input: I? = null): RootResponse<O>
}