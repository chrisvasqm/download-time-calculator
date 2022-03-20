package com.christianv07.dev.speedy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.christianv07.dev.speedy.model.DownloadTime
import kotlin.math.pow

class MainViewModel : ViewModel() {

    private var _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun calculateResult(downloadTime: DownloadTime) {
        val fileSize = convertFileSize(downloadTime)
        val estimatedSpeed = convertEstimatedSpeed(downloadTime)

        val totalSeconds = if (downloadTime.progress > 0) {
            getRemainingFileSize(downloadTime)
        } else {
            fileSize / estimatedSpeed
        }

        val hours = (totalSeconds / 3600).toInt()
        val minutes = ((totalSeconds % 3600) / 60).toInt()
        val seconds = (totalSeconds % 60).toInt()

        _result.value = "$hours:$minutes:$seconds"
    }

    private fun convertFileSize(downloadTime: DownloadTime) =
        when (downloadTime.fileSizeModifier) {
            "KB" -> downloadTime.fileSize * 2.0.pow(13.0)
            "MB" -> downloadTime.fileSize * 2.0.pow(23.0)
            "GB" -> downloadTime.fileSize * 2.0.pow(33.0)
            "TB" -> downloadTime.fileSize * 2.0.pow(43.0)
            else -> 0.0
        }

    private fun convertEstimatedSpeed(downloadTime: DownloadTime) =
        when (downloadTime.estimatedSpeedModifier) {
            "KB/s" -> downloadTime.estimatedSpeed * 2.0.pow(13.0)
            "MB/s" -> downloadTime.estimatedSpeed * 2.0.pow(23.0)
            "GB/s" -> downloadTime.estimatedSpeed * 2.0.pow(33.0)
            "TB/s" -> downloadTime.estimatedSpeed * 2.0.pow(43.0)
            else -> 0.0
        }

    private fun getRemainingFileSize(download: DownloadTime): Double {
        val percentageOfFile = download.fileSize * (download.progress / 100)
        return download.fileSize - percentageOfFile
    }

}
