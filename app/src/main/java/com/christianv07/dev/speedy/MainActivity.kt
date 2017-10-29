package com.christianv07.dev.speedy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.christianv07.dev.speedy.extension.fill
import com.christianv07.dev.speedy.extension.getDoubleOrZero
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private val context = this
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.menuAbout -> displayAboutScreen()
        R.id.menuFeedback -> sendFeedback()
        R.id.menuRate -> gotoPlayStorePage()
        else -> super.onOptionsItemSelected(item)
    }

    private fun displayAboutScreen(): Boolean {
        startActivity<AboutActivity>()
        return true
    }

    private fun sendFeedback(): Boolean {
        val feedback = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.authorEmail), null))
        feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
        startActivity(Intent.createChooser(feedback, getString(R.string.sendFeedback)))
        return true
    }

    private fun gotoPlayStorePage(): Boolean {
        val rate = Intent(Intent.ACTION_VIEW)
        rate.data = Uri.parse(getString(R.string.playstoreUrl))
        startActivity(rate)
        return true
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
        val size = when (fileSizeSpinnerValue) {
            "KB" -> byteConverter.toKB(editFilesize.getDoubleOrZero())
            "MB" -> byteConverter.toMB(editFilesize.getDoubleOrZero())
            "GB" -> byteConverter.toGB(editFilesize.getDoubleOrZero())
            "TB" -> byteConverter.toTB(editFilesize.getDoubleOrZero())
            else -> 0.0
        }

        val speed = when (spinnerEstimatedspeed.selectedItem.toString()) {
            "KB/s" -> byteConverter.toKB(editEstimatedSpeed.getDoubleOrZero())
            "MB/s" -> byteConverter.toMB(editEstimatedSpeed.getDoubleOrZero())
            "GB/s" -> byteConverter.toGB(editEstimatedSpeed.getDoubleOrZero())
            "TB/s" -> byteConverter.toTB(editEstimatedSpeed.getDoubleOrZero())
            else -> 0.0
        }

        val progress = seekbarDownloaded.progress.toDouble()

        val download = Download(size, speed, progress)

        textView_cardView_hours.text = DownloadTime.getHours(download).toString()
        textView_cardView_minutes.text = DownloadTime.getMinutes(download).toString()
        textView_cardView_seconds.text = DownloadTime.getSeconds(download).toString()
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


