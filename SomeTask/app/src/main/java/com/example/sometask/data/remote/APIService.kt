package com.example.sometask.data.remote

import com.example.sometask.data.model.BaseMovie
import retrofit2.http.GET

interface APIService {
    @GET(ApiURL.LATEST_MOVIE)
    suspend fun movieList():BaseMovie
}