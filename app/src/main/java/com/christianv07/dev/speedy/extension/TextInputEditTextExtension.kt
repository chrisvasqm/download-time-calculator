package com.christianv07.dev.speedy.extension

import android.support.design.widget.TextInputEditText

fun TextInputEditText.getDoubleOrZero(): Double {
    if (this.text.toString().isBlank() || this.text.toString() == ".")
        return 0.0

    return this.text.toString().toDouble()
}

fun TextInputEditText.getIntOrZero(): Int {
    if (this.text.toString().isBlank() || this.text.toString() == ".")
        return 0

    return this.text.toString().toInt()
}