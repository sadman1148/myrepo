package com.redenvy.justdoit.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.redenvy.justdoit.R
import com.redenvy.justdoit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            settingsIcon.setOnClickListener(){
                startActivity(Intent(applicationContext,SettingsActivity::class.java))
            }
        }
        apiCall()
    }
    private fun apiCall(){
        //TODO: find out what is supposed to be here.
    }
}