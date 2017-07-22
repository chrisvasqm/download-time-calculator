package com.christianv07.dev.speedy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun LoadSpin(spin: Spinner, spin2: Spinner) {
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner1, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter

        val adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner2, android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin2.adapter = adapter2
    }

}


