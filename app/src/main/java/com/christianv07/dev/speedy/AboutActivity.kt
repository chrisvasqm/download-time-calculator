package com.christianv07.dev.speedy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        txtAppVersion.text = BuildConfig.VERSION_NAME

        linearForkGithub.setOnClickListener { navigateToGitHubRepository() }
        linearTwitter.setOnClickListener { navigateToTwitterProfile() }
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
