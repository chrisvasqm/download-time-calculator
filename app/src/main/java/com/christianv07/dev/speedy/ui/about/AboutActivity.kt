package com.christianv07.dev.speedy.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.christianv07.dev.speedy.BuildConfig
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.txtAppVersion.text = BuildConfig.VERSION_NAME

        binding.linearForkGithub.setOnClickListener { navigateToGitHubRepository() }
        binding.linearTwitter.setOnClickListener { navigateToTwitterProfile() }
    }

    private fun navigateToTwitterProfile() {
        val twitterProfile = Intent(Intent.ACTION_VIEW)
        twitterProfile.data = Uri.parse(getString(R.string.url_profile_twitter))
        startActivity(twitterProfile)
    }

    private fun navigateToGitHubRepository() {
        val repository = Intent(Intent.ACTION_VIEW)
        repository.data = Uri.parse(getString(R.string.url_github_repository))
        startActivity(repository)
    }
}
