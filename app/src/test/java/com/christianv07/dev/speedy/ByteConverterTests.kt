package com.christianv07.dev.speedy

import junit.framework.Assert.assertEquals
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

        assertEquals(expected, actual)
    }

    @Test
    fun toMB_ConvertDouble_ReturnAsMegabytes() {
        val expected = 8388608.0
        val actual = byteConverter?.toMB(1.0)

        assertEquals(expected, actual)
    }

    @Test
    fun toGB_ConvertDouble_ReturnAsGigabytes() {
        val expected = 8589934592.0
        val actual = byteConverter?.toGB(1.0)

        assertEquals(expected, actual)
    }

    @Test
    fun toTB_ConvertDouble_ReturnAsTerabytes() {
        val expected = 8.796093022208E12
        val actual = byteConverter?.toTB(1.0)

        assertEquals(expected, actual)
    }

    @After
    fun tearDown() {
        byteConverter = null
    }

}
