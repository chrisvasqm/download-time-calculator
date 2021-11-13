package com.christianv07.dev.speedy

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ByteConverterTests {
    private var byteConverter: ByteConverter? = null

    @Before
    fun setUp() {
        byteConverter = ByteConverter()
    }

    @Test
    fun toKB_ConvertDouble_ReturnAsKilobytes() {
        val expected = 8192.0
        val actual = byteConverter?.toKB(1.0)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun toMB_ConvertDouble_ReturnAsMegabytes() {
        val expected = 8388608.0
        val actual = byteConverter?.toMB(1.0)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun toGB_ConvertDouble_ReturnAsGigabytes() {
        val expected = 8589934592.0
        val actual = byteConverter?.toGB(1.0)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun toTB_ConvertDouble_ReturnAsTerabytes() {
        val expected = 8.796093022208E12
        val actual = byteConverter?.toTB(1.0)

        assertThat(actual).isEqualTo(expected)
    }

    @After
    fun tearDown() {
        byteConverter = null
    }

}
