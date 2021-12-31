package com.example.sometask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sometask.databinding.ActivityMainBinding
import com.example.sometask.ui.adapter.MovieAdapter
import com.example.sometask.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var pageNumber: Int = 1
    private var totalPage: Int = 0
    private var isLoading: Boolean = false
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callTheApi()
        binding.recyclerviewMoviesList.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter
        }
        binding.recyclerviewMoviesList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading && pageNumber <= totalPage) {
                    isLoading = true
                    movieViewModel.getMovies(pageNumber++)
                }
            }
        })
//        movieAdapter.onItemClick = { movieItem ->
//            val gson = Gson()
//            val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java).apply {
//                putExtra("movie", gson.toJson(movieItem))
//            }
//            startActivity(intent)
//        }
    }

    private fun callTheApi() {
        movieViewModel.getMovies(pageNumber++)
        movieViewModel.movie.observe(this) {
            when (it) {
                is DataState.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    totalPage = it.data.totalPages
                    movieAdapter.addItems(it.data.results)
                    isLoading = false
                }
                is DataState.Error -> {
                    Toast.makeText(this, "API failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}