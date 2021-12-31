package com.example.sometask.data.remote

import com.example.sometask.data.model.BaseMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(ApiURL.LATEST_MOVIE)
    suspend fun movieList(@Query("page") page:Int):BaseMovie
}