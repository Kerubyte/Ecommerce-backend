package com.kerubyte.feature.auth.request

import com.google.common.truth.Truth
import com.kerubyte.testData.FakeRequest.fakeInvalidAuthRequest
import com.kerubyte.testData.FakeRequest.fakeValidAuthRequest
import org.junit.jupiter.api.Test

class AuthRequestTest {

    @Test
    fun `given valid request should return True`() {
        val validRequest = fakeValidAuthRequest()

        val result = validRequest.isValidRequest()
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `given invalid request should return False`() {
        val invalidRequest = fakeInvalidAuthRequest()

        val result = invalidRequest.isValidRequest()
        Truth.assertThat(result).isFalse()
    }
}