package com.christianv07.dev.speedy.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.databinding.ActivityMainBinding
import com.christianv07.dev.speedy.extensions.toDouble
import com.christianv07.dev.speedy.model.DownloadTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupProgressBar()
        setupFileSizeTextWatcher()
        setupEstimatedSpeedTextWatcher()
        setupFileSizeSpinnerListener()
        setupEstimatedSpeedSpinnerListener()

        viewModel.result.observe(this) {
            binding.textViewResult?.text = it
        }

        binding.btnClear.setOnClickListener {
            resetFields()
        }
    }

    private fun setupProgressBar() {
        binding.seekbarDownloaded.max = 100

        binding.seekbarDownloaded.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                binding.seekbarDownloaded.progress = progress
                binding.textViewProgress.text = "$progress%"
                viewModel.calculateResult(getFormData())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun setupFileSizeTextWatcher() {
        binding.editFilesize.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.calculateResult(getFormData())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun setupEstimatedSpeedTextWatcher() {
        binding.editEstimatedSpeed.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.calculateResult(getFormData())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun resetFields() {
        binding.editFilesize.setText("")
        binding.editEstimatedSpeed.setText("")
        binding.seekbarDownloaded.progress = 0
    }

    private fun getFormData() = DownloadTime(
        fileSize = binding.editFilesize.text.toDouble(),
        estimatedSpeed = binding.editEstimatedSpeed.text.toDouble(),
        progress = binding.seekbarDownloaded.progress.toDouble(),
        fileSizeModifier = binding.spinnerFilesize.selectedItem.toString(),
        estimatedSpeedModifier = binding.spinnerEstimatedspeed.selectedItem.toString()
    )

    private fun setupFileSizeSpinnerListener() {
        binding.spinnerFilesize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.calculateResult(getFormData())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    private fun setupEstimatedSpeedSpinnerListener() {
        binding.spinnerEstimatedspeed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.calculateResult(getFormData())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }


}

