package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.model.Download
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DownloadTimeTests {

    @Test
    fun getHoursEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)
        val downloadTime = DownloadTime(download)

        val expected = 0
        val actual = downloadTime.getHours()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getHoursEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)
        val time = DownloadTime(download)

        val expected = 0
        val actual = time.getHours()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getHoursDownloadProgressIsZeroReturnsHours() {
        val download = Download(100000.0, 2.0, 0.0)
        val time = DownloadTime(download)

        val expected = 13
        val actual = time.getHours()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getHoursDownloadProgressIsGreaterThanZeroReturnsHours() {
        val download = Download(100000.0, 2.0, 50.0)
        val time = DownloadTime(download)

        val expected = 6
        val actual = time.getHours()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getMinutesEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)
        val time = DownloadTime(download)

        val expected = 0
        val actual = time.getMinutes()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getMinutesEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)
        val time = DownloadTime(download)

        val expected = 0
        val actual = time.getMinutes()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getMinutesDownloadProgressIsZeroReturnsMinutes() {
        val download = Download(100000.0, 2.0, 0.0)
        val time = DownloadTime(download)

        val expected = 53
        val actual = time.getMinutes()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getMinutesDownloadProgressIsGreaterThanZeroReturnsMinutes() {
        val download = Download(100000.0, 2.0, 50.0)
        val time = DownloadTime(download)

        val expected = 56
        val actual = time.getMinutes()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getSecondsEstimatedSpeedIsZeroReturnsZero() {
        val download = Download(100000.0, 0.0, 0.0)
        val time = DownloadTime(download)

        val expected = 0
        val actual = time.getSeconds()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getSecondsEstimatedSpeedIsNegativeReturnsZero() {
        val download = Download(100000.0, -5.0, 0.0)
        val time = DownloadTime(download)

        val expected = 0
        val actual = time.getSeconds()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getSecondsDownloadProgressIsZeroReturnsSeconds() {
        val download = Download(100000.0, 2.0, 0.0)
        val time = DownloadTime(download)

        val expected = 20
        val actual = time.getSeconds()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getSecondsDownloadProgressIsGreaterThanZeroReturnsSeconds() {
        val download = Download(100000.0, 2.0, 50.0)
        val time = DownloadTime(download)

        val expected = 40
        val actual = time.getSeconds()

        assertThat(actual).isEqualTo(expected)
    }

}