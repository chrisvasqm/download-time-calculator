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

        spinnerFilesize.fill(R.array.file_sizes)
        spinnerEstimatedspeed.fill(R.array.estimated_speeds)
        spinnerFilesize.onItemSelectedListener = spinnerOnItemSelectedListener
        spinnerEstimatedspeed.onItemSelectedListener = spinnerOnItemSelectedListener

        editFilesize.addTextChangedListener(textWatcher)
        editEstimatedSpeed.addTextChangedListener(textWatcher)

        seekbarDownloaded.setOnSeekBarChangeListener(onSeekBarChangeListener)

        btnShare.setOnClickListener { shareDownloadDetails() }
        btnClear.setOnClickListener { setDefaultViewValues() }
    }

    private fun setDefaultViewValues() {
        spinnerFilesize.fill(R.array.file_sizes)
        spinnerEstimatedspeed.fill(R.array.estimated_speeds)
        editFilesize.setText("")
        editEstimatedSpeed.setText("")
        seekbarDownloaded.progress = 0
    }

    private fun updateDownloadTime() {
        val fileSizeSpinnerValue = spinnerFilesize.selectedItem.toString()
        val fileSize = when (fileSizeSpinnerValue) {
            "KB" -> byteConverter.toKB(editFilesize.getDoubleOrZero())
            "MB" -> byteConverter.toMB(editFilesize.getDoubleOrZero())
            "GB" -> byteConverter.toGB(editFilesize.getDoubleOrZero())
            "TB" -> byteConverter.toTB(editFilesize.getDoubleOrZero())
            else -> 0.0
        }

        val estimatedSpeed = when (spinnerEstimatedspeed.selectedItem.toString()) {
            "KB/s" -> byteConverter.toKB(editEstimatedSpeed.getDoubleOrZero())
            "MB/s" -> byteConverter.toMB(editEstimatedSpeed.getDoubleOrZero())
            "GB/s" -> byteConverter.toGB(editEstimatedSpeed.getDoubleOrZero())
            "TB/s" -> byteConverter.toTB(editEstimatedSpeed.getDoubleOrZero())
            else -> 0.0
        }

        val downloadProgress = seekbarDownloaded.progress.toDouble()

        val downloadTimeCalculator = DownloadTimeCalculator(fileSize, estimatedSpeed, downloadProgress)

        //TODO: add formatted to display hours with commas every 3 digits
        textView_cardView_hours.text = downloadTimeCalculator.getHours().toString()
        textView_cardView_minutes.text = downloadTimeCalculator.getMinutes().toString()
        textView_cardView_seconds.text = downloadTimeCalculator.getSeconds().toString()
    }

    private fun shareDownloadDetails() {
        val fileSize = "${editFilesize.text}${spinnerFilesize.selectedItem}"
        val time = "${textView_cardView_hours.text}:${textView_cardView_minutes.text}:${textView_cardView_seconds.text}"
        val estimatedSpeed = "${editEstimatedSpeed.text}${spinnerEstimatedspeed.selectedItem}"
        val progress = seekbarDownloaded.progress.toString()
        val downloadDetails = "A file of $fileSize would take $time to download/transfer at a speed of $estimatedSpeed with $progress% already downloaded."

        val sendDetailsIntent = Intent()
        sendDetailsIntent.action = Intent.ACTION_SEND
        sendDetailsIntent.putExtra(Intent.EXTRA_TEXT, downloadDetails)
        sendDetailsIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendDetailsIntent, getString(R.string.share)))
    }
}


