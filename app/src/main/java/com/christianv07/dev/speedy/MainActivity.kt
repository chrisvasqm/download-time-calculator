package com.christianv07.dev.speedy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.christianv07.dev.speedy.extension.fill
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        spinner_filesize.fill(R.array.file_sizes)
        spinner_estimatedspeed.fill(R.array.estimated_speeds)

        seekbar_main_downloaded.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                textview_percent.text = "$progress%"
            }
        })

    }
}


