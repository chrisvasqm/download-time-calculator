package com.christianv07.dev.speedy

import junit.framework.Assert.assertEquals
import org.junit.Test

class DownloadTimeCalculatorTests {

    @Test
    fun getHours_EstimatedSpeedIsZero_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 0.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getHours()

        assertEquals(expected, actual)
    }

    @Test
    fun getHours_EstimatedSpeedIsNegative_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = -5.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getHours()

        assertEquals(expected, actual)
    }

    @Test
    fun getHours_DownloadProgressIsZero_ReturnsHours() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 0.0)

        val expected = 13
        val actual = downloadTimeCalculator.getHours()

        assertEquals(expected, actual)
    }

    @Test
    fun getHours_DownloadProgressIsGreaterThanZero_ReturnsHours() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 50.0)

        val expected = 6
        val actual = downloadTimeCalculator.getHours()

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutes_EstimatedSpeedIsZero_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 0.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getMinutes()

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutes_EstimatedSpeedIsNegative_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = -5.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getMinutes()

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutes_DownloadProgressIsZero_ReturnsMinutes() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 0.0)

        val expected = 53
        val actual = downloadTimeCalculator.getMinutes()

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutes_DownloadProgressIsGreaterThanZero_ReturnsMinutes() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 50.0)

        val expected = 56
        val actual = downloadTimeCalculator.getMinutes()

        assertEquals(expected, actual)
    }

    @Test
    fun getSeconds_EstimatedSpeedIsZero_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 0.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getSeconds()

        assertEquals(expected, actual)
    }

    @Test
    fun getSeconds_EstimatedSpeedIsNegative_ReturnsZero() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = -5.0, downloadProgress = 0.0)

        val expected = 0
        val actual = downloadTimeCalculator.getSeconds()

        assertEquals(expected, actual)
    }

    @Test
    fun getSeconds_DownloadProgressIsZero_ReturnsSeconds() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 0.0)

        val expected = 20
        val actual = downloadTimeCalculator.getSeconds()

        assertEquals(expected, actual)
    }

    @Test
    fun getSeconds_DownloadProgressIsGreaterThanZero_ReturnsSeconds() {
        val downloadTimeCalculator = DownloadTimeCalculator(fileSize = 100000.0, estimatedSpeed = 2.0, downloadProgress = 50.0)

        val expected = 40
        val actual = downloadTimeCalculator.getSeconds()

        assertEquals(expected, actual)
    }

}