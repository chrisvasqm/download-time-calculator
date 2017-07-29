package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.extension.isZeroOrNegative

class DownloadTimeCalculator {

    fun calculateHours(fileSize: Double, downloadSpeed: Double): Double {
        val MINUTES_IN_HOUR = 60
        return calculateMinutes(fileSize, downloadSpeed) / MINUTES_IN_HOUR
    }

    fun calculateMinutes(fileSize: Double, downloadSpeed: Double): Double {
        val SECONDS_IN_MINUTES = 60
        return calculateSeconds(fileSize, downloadSpeed) / SECONDS_IN_MINUTES
    }

    fun calculateSeconds(fileSize: Double, downloadSpeed: Double): Double {
        return when {
            downloadSpeed.isZeroOrNegative() -> 0.0
            else -> fileSize / downloadSpeed
        }
    }
}
