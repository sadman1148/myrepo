package com.example.sometask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sometask.R
import com.example.sometask.databinding.ActivityMainBinding
import com.example.sometask.utils.DataState

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val movieViewModel:MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callTheApi()
    }

    private fun callTheApi(){
        binding.button.setOnClickListener {
            movieViewModel.movie.observe(this) {
                when (it) {
                    is DataState.Loading -> {
                        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is DataState.Success -> {
                        Toast.makeText(this, "${it.data.results.first().title}", Toast.LENGTH_SHORT).show()
                    }
                    is DataState.Error -> {
                        Toast.makeText(this, "API failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}