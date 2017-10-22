package com.christianv07.dev.speedy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.christianv07.dev.speedy.extension.fill
import com.christianv07.dev.speedy.extension.getDoubleOrZero
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val byteConverter = ByteConverter()
    private val spinnerOnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            updateDownloadTime()
        }
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            updateDownloadTime()
        }
    }
    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
            textview_percent.text = "$progress%"
            updateDownloadTime()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        spinner_filesize.fill(R.array.file_sizes)
        spinner_estimatedspeed.fill(R.array.estimated_speeds)
        spinner_filesize.onItemSelectedListener = spinnerOnItemSelectedListener
        spinner_estimatedspeed.onItemSelectedListener = spinnerOnItemSelectedListener

        edittext_filesize.addTextChangedListener(textWatcher)
        edittext_estimatedSpeed.addTextChangedListener(textWatcher)

        seekbar_main_downloaded.setOnSeekBarChangeListener(onSeekBarChangeListener)

        imgBtnShare.setOnClickListener { shareDownloadDetails() }
    }

    private fun updateDownloadTime() {
        val fileSize = when (spinner_filesize.selectedItem.toString()) {
            "KB" -> byteConverter.toKB(edittext_filesize.getDoubleOrZero())
            "MB" -> byteConverter.toMB(edittext_filesize.getDoubleOrZero())
            "GB" -> byteConverter.toGB(edittext_filesize.getDoubleOrZero())
            "TB" -> byteConverter.toTB(edittext_filesize.getDoubleOrZero())
            else -> 0.0
        }

        val estimatedSpeed = when (spinner_estimatedspeed.selectedItem.toString()) {
            "KB/s" -> byteConverter.toKB(edittext_estimatedSpeed.getDoubleOrZero())
            "MB/s" -> byteConverter.toMB(edittext_estimatedSpeed.getDoubleOrZero())
            "GB/s" -> byteConverter.toGB(edittext_estimatedSpeed.getDoubleOrZero())
            "TB/s" -> byteConverter.toTB(edittext_estimatedSpeed.getDoubleOrZero())
            else -> 0.0
        }

        val downloadProgress = seekbar_main_downloaded.progress.toDouble()

        val downloadTimeCalculator = DownloadTimeCalculator(fileSize, estimatedSpeed, downloadProgress)

        //TODO: add formatted to display hours with commas every 3 digits
        textView_cardView_hours.text = downloadTimeCalculator.getHours().toString()
        textView_cardView_minutes.text = downloadTimeCalculator.getMinutes().toString()
        textView_cardView_seconds.text = downloadTimeCalculator.getSeconds().toString()
    }

    private fun shareDownloadDetails() {
        val fileSize = "${edittext_filesize.text}${spinner_filesize.selectedItem}"
        val time = "${textView_cardView_hours.text}:${textView_cardView_minutes.text}:${textView_cardView_seconds.text}"
        val estimatedSpeed = "${edittext_estimatedSpeed.text}${spinner_estimatedspeed.selectedItem}"
        val progress = seekbar_main_downloaded.progress.toString()
        val downloadDetails = "A file of $fileSize would take $time to download/transfer at a speed of $estimatedSpeed with $progress% already downloaded."

        val sendDetailsIntent = Intent()
        sendDetailsIntent.action = Intent.ACTION_SEND
        sendDetailsIntent.putExtra(Intent.EXTRA_TEXT, downloadDetails)
        sendDetailsIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendDetailsIntent, getString(R.string.share)))
    }
}


