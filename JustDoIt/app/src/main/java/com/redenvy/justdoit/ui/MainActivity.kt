package com.redenvy.justdoit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.ActivityMainBinding
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTodo()

        viewModel.todoLists.observe(this){
            when (it) {
                is DataState.Loading -> {
                    Timber.e("Loading")
                }
                is DataState.Success -> {
                    it.data.forEach{
                        Timber.e("Time: ${it.time}\nTitle: ${it.title}")
                    }
                }
                is DataState.Error -> {
                    Timber.e("Error")
                }
            }
        }

        binding.apply {
            settingsIcon.setOnClickListener(){
                startActivity(Intent(applicationContext,SettingsActivity::class.java))
            }
        }
    }
}