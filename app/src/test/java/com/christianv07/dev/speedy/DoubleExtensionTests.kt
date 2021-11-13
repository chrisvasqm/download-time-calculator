package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.extension.isZeroOrNegative
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DoubleExtensionTests {

    @Test
    fun isZeroOrNegative_isZero_ReturnsTrue() =
        assertThat(0.0.isZeroOrNegative()).isTrue()

    @Test
    fun isZeroOrNegative_isGreaterThanZero_ReturnsFalse() =
        assertThat(5.0.isZeroOrNegative()).isFalse()

}