package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.extension.isZeroOrNegative
import com.christianv07.dev.speedy.model.Download

class DownloadTime {

    companion object {
        fun getHours(download: Download) = when {
            download.speed.isZeroOrNegative() -> 0
            download.progress > 0 -> ((getRemainingFileSize(download) / download.speed) / 3600).toInt()
            else -> ((download.size / download.speed) / 3600).toInt()
        }

        fun getMinutes(download: Download) = when {
            download.speed.isZeroOrNegative() -> 0
            download.progress > 0 -> (((getRemainingFileSize(download) / download.speed) / 60) % 60).toInt()
            else -> (((download.size / download.speed) / 60) % 60).toInt()
        }

        fun getSeconds(download: Download) = when {
            download.speed.isZeroOrNegative() -> 0
            download.progress > 0 -> ((getRemainingFileSize(download) / download.speed) % 60).toInt()
            else -> ((download.size / download.speed) % 60).toInt()
        }

        private fun getRemainingFileSize(download: Download): Double {
            val percentageOfFile = download.size * (download.progress / 100)
            return download.size - percentageOfFile
        }
    }

}
