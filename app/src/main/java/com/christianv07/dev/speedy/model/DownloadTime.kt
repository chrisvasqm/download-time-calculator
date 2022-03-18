package com.christianv07.dev.speedy.model

data class DownloadTime(
    var fileSize: Double,
    var estimatedSpeed: Double,
    var progress: Int,
    var fileSizeModifier: String,
    var estimatedSpeedModifier: String
)