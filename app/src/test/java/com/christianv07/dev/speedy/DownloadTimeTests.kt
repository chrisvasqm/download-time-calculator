package com.christianv07.dev.speedy

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DownloadTimeTests {

    @Test
    fun getHoursEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getHours(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getHoursEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getHours(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getHoursDownloadProgressIsZeroReturnsHours() {
        val download = Download(100000.0, 2.0, 0.0)

        val expected = 13
        val actual = DownloadTime.getHours(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getHoursDownloadProgressIsGreaterThanZeroReturnsHours() {
        val download = Download(100000.0, 2.0, 50.0)

        val expected = 6
        val actual = DownloadTime.getHours(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getMinutes(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getMinutes(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesDownloadProgressIsZeroReturnsMinutes() {
        val download = Download(100000.0, 2.0, 0.0)

        val expected = 53
        val actual = DownloadTime.getMinutes(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesDownloadProgressIsGreaterThanZeroReturnsMinutes() {
        val download = Download(100000.0, 2.0, 50.0)

        val expected = 56
        val actual = DownloadTime.getMinutes(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getSecondsEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getSeconds(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getSecondsEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)

        val expected = 0
        val actual = DownloadTime.getSeconds(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getSecondsDownloadProgressIsZeroReturnsSeconds() {
        val download = Download(100000.0, 2.0, 0.0)

        val expected = 20
        val actual = DownloadTime.getSeconds(download)

        assertEquals(expected, actual)
    }

    @Test
    fun getSecondsDownloadProgressIsGreaterThanZeroReturnsSeconds() {
        val download = Download(100000.0, 2.0, 50.0)

        val expected = 40
        val actual = DownloadTime.getSeconds(download)

        assertEquals(expected, actual)
    }

}