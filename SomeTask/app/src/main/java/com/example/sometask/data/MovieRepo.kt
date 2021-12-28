package com.example.sometask.data

import com.example.sometask.data.model.BaseMovie
import com.example.sometask.data.remote.APIService
import com.example.sometask.utils.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiService: APIService){
    fun movieList()=flow<DataState<BaseMovie>> {
        emit(DataState.Loading)
        try {
            val result = apiService.movieList()
            emit(DataState.Success(result))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}