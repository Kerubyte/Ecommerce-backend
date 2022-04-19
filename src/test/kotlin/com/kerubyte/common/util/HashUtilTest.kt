package com.kerubyte.common.util

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class HashUtilTest {

    @Test
    fun `given string returns hashString`() {
        val inputString = "test@example.com"
        val expectedHash = "567159d622ffbb50b11b0efd307be358624a26ee"

        val resultHash = getHashFromString(inputString)
        assertThat(resultHash).isEqualTo(expectedHash)
    }

    @Test
    fun `given correct password returns True`() {
        val inputString = "password"
        val expectedHash = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"

        val result = isPasswordHashMatch(inputString, expectedHash)
        assertThat(result).isTrue()
    }
}