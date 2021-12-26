package com.example.sometask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sometask.data.MovieRepo
import com.example.sometask.data.model.BaseMovie
import com.example.sometask.utils.DataState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class MovieViewModel @Inject constructor(private val repo: MovieRepo) : ViewModel() {
    private val movies = MutableLiveData<DataState<BaseMovie>>()
    val movie:LiveData<DataState<BaseMovie>> get() = movies

    fun getMovies(){
        viewModelScope.launch { repo.movieList().onEach {
            movies.value = it
        } }
    }
}