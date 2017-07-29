package com.christianv07.dev.speedy.extension

import android.support.design.widget.TextInputEditText

fun TextInputEditText.getDoubleOrZero(): Double {
    if (this.text.toString().isBlank())
        return 0.0

    return this.text.toString().toDouble()
}