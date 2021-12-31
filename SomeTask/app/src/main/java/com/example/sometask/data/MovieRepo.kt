package com.example.sometask.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sometask.data.model.BaseMovie
import com.example.sometask.data.remote.APIService
import com.example.sometask.utils.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiService: APIService){
    fun movieList(pageNumber: Int)= flow<DataState<BaseMovie>> {
        emit(DataState.Loading)
        try {
            val result = apiService.movieList(pageNumber)
            emit(DataState.Success(result))
        }catch (exception:Exception){
            emit(DataState.Error(exception))
        }
    }
//    fun movieList() = Pager(
//    config = PagingConfig(
//        pageSize = 20,
//        maxSize = 100,
//        enablePlaceholders = false
//    ), pagingSourceFactory = {PagingSource(apiService)}
//    ).liveData
}