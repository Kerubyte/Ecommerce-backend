package com.kerubyte.feature.auth.request

import com.google.common.truth.Truth.assertThat
import com.kerubyte.testData.FakeRequest.fakeInvalidLoginRequest
import com.kerubyte.testData.FakeRequest.fakeValidLoginRequest
import org.junit.jupiter.api.Test

class LoginRequestTest {

    @Test
    fun `valid request should return True`() {
        val validRequest = fakeValidLoginRequest()

        val result = validRequest.isValidRequest()
        assertThat(result).isTrue()
    }

    @Test
    fun `invalid request should return False`() {
        val invalidRequest = fakeInvalidLoginRequest()

        val result = invalidRequest.isValidRequest()
        assertThat(result).isFalse()
    }
}