package com.christianv07.dev.speedy

class ByteConverter {

    fun toKB(number: Double): Double {
        return number * Math.pow(2.0, 13.0)
    }

    fun toMB(number: Double): Double {
        return number * Math.pow(2.0, 23.0)
    }

    fun toGB(number: Double): Double {
        return number * Math.pow(2.0, 33.0)
    }

    fun toTB(number: Double): Double {
        return number * Math.pow(2.0, 43.0)
    }

}