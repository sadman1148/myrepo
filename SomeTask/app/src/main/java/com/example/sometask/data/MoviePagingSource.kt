package com.example.sometask.data

import com.example.sometask.data.model.Result
import com.example.sometask.data.remote.APIService
import retrofit2.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

//class PagingSource (private val apiService:APIService):PagingSource<Int,Result>(){
//    Override suspend fun load(params: LoadParams<Int>):LoadResult<Int, Result>){
//        val position = params.key ?: STARTING_PAGE_INDEX
//
//        return try{
//            val baseMovie = apiService.movieList(position)
//            val movies = baseMovie.Result
//            LoadResult.Page(
//                data = movies,
//                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = if (movies.isEmpty()) null else position + 1
//            )
//        }
//        catch (exception : IOException){
//            LoadResult.Error(exception)
//        }
//        catch (exception : HttpException){
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
//        TODO("Not yet implemented")
//    }
//}