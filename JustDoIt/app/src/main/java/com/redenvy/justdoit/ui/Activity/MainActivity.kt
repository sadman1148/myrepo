package com.redenvy.justdoit.ui.Activity

import android.content.IntentFilter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redenvy.justdoit.databinding.ActivityMainBinding
import com.redenvy.justdoit.services.Broadcaster
import com.redenvy.justdoit.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener
    private lateinit var sharedPreferences: SharedPreferences

    private val broadcastReceiver by lazy {
        Broadcaster()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(broadcastReceiver, IntentFilter(Constants.INTENT_ACTION))
    }
//    override fun onResume() {
//        super.onResume()
//        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener)
//    }
}