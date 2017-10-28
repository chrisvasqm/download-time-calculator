package com.christianv07.dev.speedy

data class Download(
        val size: Double,
        val speed: Double,
        val progress: Double = 0.0
)