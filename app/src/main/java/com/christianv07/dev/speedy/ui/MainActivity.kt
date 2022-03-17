package com.christianv07.dev.speedy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.christianv07.dev.speedy.ByteConverter
import com.christianv07.dev.speedy.DownloadTime
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.databinding.ActivityMainBinding
import com.christianv07.dev.speedy.extension.fill
import com.christianv07.dev.speedy.extension.getDoubleOrZero
import com.christianv07.dev.speedy.extension.getIntOrZero
import com.christianv07.dev.speedy.model.Download

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
            binding.textviewPercent.text = "$progress%"
            updateDownloadTime()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menuAbout -> displayAboutScreen()
        R.id.menuFeedback -> sendFeedback()
        R.id.menuRate -> gotoPlayStorePage()
        else -> super.onOptionsItemSelected(item)
    }

    private fun displayAboutScreen(): Boolean {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun sendFeedback(): Boolean {
        val feedback = Intent(
            Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", getString(R.string.author_email), null)
        )
        feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
        startActivity(Intent.createChooser(feedback, getString(R.string.send_feedback)))
        return true
    }

    private fun gotoPlayStorePage(): Boolean {
        val rate = Intent(Intent.ACTION_VIEW)
        rate.data = Uri.parse(getString(R.string.url_playstore))
        startActivity(rate)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.spinnerFilesize.fill(R.array.file_sizes)
        binding.spinnerEstimatedspeed.fill(R.array.estimated_speeds)
        binding.spinnerFilesize.onItemSelectedListener = spinnerOnItemSelectedListener
        binding.spinnerEstimatedspeed.onItemSelectedListener = spinnerOnItemSelectedListener

        binding.editFilesize.addTextChangedListener(textWatcher)
        binding.editEstimatedSpeed.addTextChangedListener(textWatcher)

        binding.seekbarDownloaded.setOnSeekBarChangeListener(onSeekBarChangeListener)

        binding.btnShare.setOnClickListener { shareDownloadDetails() }
        binding.btnClear.setOnClickListener { setDefaultViewValues() }
    }

    private fun setDefaultViewValues() {
        binding.spinnerFilesize.fill(R.array.file_sizes)
        binding.spinnerEstimatedspeed.fill(R.array.estimated_speeds)
        binding.editFilesize.setText("")
        binding.editEstimatedSpeed.setText("")
        binding.seekbarDownloaded.progress = 0
    }

    private fun updateDownloadTime() {
        val size = when (binding.spinnerFilesize.selectedItem.toString()) {
            "KB" -> byteConverter.toKB(binding.editFilesize.getDoubleOrZero())
            "MB" -> byteConverter.toMB(binding.editFilesize.getDoubleOrZero())
            "GB" -> byteConverter.toGB(binding.editFilesize.getDoubleOrZero())
            "TB" -> byteConverter.toTB(binding.editFilesize.getDoubleOrZero())
            else -> 0.0
        }

        val speed = when (binding.spinnerEstimatedspeed.selectedItem.toString()) {
            "KB/s" -> byteConverter.toKB(binding.editEstimatedSpeed.getDoubleOrZero())
            "MB/s" -> byteConverter.toMB(binding.editEstimatedSpeed.getDoubleOrZero())
            "GB/s" -> byteConverter.toGB(binding.editEstimatedSpeed.getDoubleOrZero())
            "TB/s" -> byteConverter.toTB(binding.editEstimatedSpeed.getDoubleOrZero())
            else -> 0.0
        }

        val progress = binding.seekbarDownloaded.progress.toDouble()

        val download = Download(size, speed, progress)

        binding.txtCardHours.text = DownloadTime.getHours(download).toString()
        binding.txtCardMinutes.text = DownloadTime.getMinutes(download).toString()
        binding.txtCardSeconds.text = DownloadTime.getSeconds(download).toString()
    }

    private fun shareDownloadDetails() {
        val fileSize =
            "${binding.editFilesize.getIntOrZero()}${binding.spinnerFilesize.selectedItem}"
        val time =
            "${binding.txtCardHours.text}:${binding.txtCardMinutes.text}:${binding.txtCardSeconds.text}"
        val estimatedSpeed =
            "${binding.editEstimatedSpeed.getIntOrZero()}${binding.spinnerEstimatedspeed.selectedItem}"
        val progress = binding.seekbarDownloaded.progress.toString()
        val downloadDetails = "File Size: $fileSize \nDownload Speed: $estimatedSpeed \n" +
                "Time to download: $time \nCurrent progress: $progress% \n\nWant to find it out " +
                "yourself? Get the app at: https://goo.gl/oRZ1xD"

        val sendDetailsIntent = Intent()
        sendDetailsIntent.action = Intent.ACTION_SEND
        sendDetailsIntent.putExtra(Intent.EXTRA_TEXT, downloadDetails)
        sendDetailsIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendDetailsIntent, getString(R.string.share)))
    }
}


