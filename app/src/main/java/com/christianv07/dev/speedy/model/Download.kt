package com.christianv07.dev.speedy.model

data class Download(
        val size: Double,
        val speed: Double,
        val progress: Double = 0.0
)