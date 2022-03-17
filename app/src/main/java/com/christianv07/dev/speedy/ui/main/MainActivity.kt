package com.christianv07.dev.speedy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.databinding.ActivityMainBinding
import com.christianv07.dev.speedy.extension.fill
import com.christianv07.dev.speedy.extension.getIntOrZero
import com.christianv07.dev.speedy.ui.about.AboutActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.spinnerFilesize.fill(R.array.file_sizes)
        binding.spinnerEstimatedspeed.fill(R.array.estimated_speeds)

        binding.btnShare.setOnClickListener { shareDownloadDetails() }
        binding.btnClear.setOnClickListener { resetUI() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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

    private fun resetUI() {
        binding.spinnerFilesize.fill(R.array.file_sizes)
        binding.spinnerEstimatedspeed.fill(R.array.estimated_speeds)
        binding.editFilesize.setText("")
        binding.editEstimatedSpeed.setText("")
        binding.seekbarDownloaded.progress = 0
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAbout)
            goToAboutScreen()
        if (item.itemId == R.id.menuFeedback)
            sendFeedback()
        if (item.itemId == R.id.menuRate)
            gotoPlayStorePage()

        return super.onOptionsItemSelected(item)
    }

    private fun goToAboutScreen() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun sendFeedback() {
        val feedback = Intent(
            Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", getString(R.string.author_email), null)
        )
        feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
        startActivity(Intent.createChooser(feedback, getString(R.string.send_feedback)))
    }

    private fun gotoPlayStorePage() {
        val rate = Intent(Intent.ACTION_VIEW)
        rate.data = Uri.parse(getString(R.string.url_playstore))
        startActivity(rate)
    }
}


