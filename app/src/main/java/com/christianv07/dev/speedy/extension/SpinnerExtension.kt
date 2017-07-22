package com.christianv07.dev.speedy.extension

import android.widget.ArrayAdapter
import android.widget.Spinner

fun Spinner.fill(array: Int) {
    val adapter = ArrayAdapter.createFromResource(context,
            array, android.R.layout.simple_spinner_item)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
}