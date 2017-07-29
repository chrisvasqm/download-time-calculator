package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.extension.isZeroOrNegative

class DownloadTimeCalculator(val fileSize: Double, val estimatedSpeed: Double, val downloadProgress: Double = 0.0) {

    fun getHours(): Int {
        return when {
            estimatedSpeed.isZeroOrNegative() -> 0
            downloadProgress > 0 -> ((getRemainingFileSize() / estimatedSpeed) / 3600).toInt()
            else -> ((fileSize / estimatedSpeed) / 3600).toInt()
        }
    }

    fun getMinutes(): Int {
        return when {
            estimatedSpeed.isZeroOrNegative() -> 0
            downloadProgress > 0 -> (((getRemainingFileSize() / estimatedSpeed) / 60) % 60).toInt()
            else -> (((fileSize / estimatedSpeed) / 60) % 60).toInt()
        }
    }

    fun getSeconds(): Int {
        return when {
            estimatedSpeed.isZeroOrNegative() -> 0
            downloadProgress > 0 -> ((getRemainingFileSize() / estimatedSpeed) % 60).toInt()
            else -> ((fileSize / estimatedSpeed) % 60).toInt()
        }
    }

    private fun getRemainingFileSize(): Double {
        val percentageOfFile = fileSize * (downloadProgress / 100)
        return fileSize - percentageOfFile
    }
}
