package com.christianv07.dev.speedy.extensions

import android.text.Editable

fun Editable?.toDouble(): Double {
    if (this.toString().isEmpty())
        return 0.0

    return this.toString().toDouble()
}