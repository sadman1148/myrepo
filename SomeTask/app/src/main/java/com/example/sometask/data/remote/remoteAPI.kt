package com.example.sometask.data.remote

import com.example.sometask.data.apiURL
import com.example.sometask.data.model.BaseMovie
import retrofit2.http.GET

interface remoteAPI{
    @GET(apiURL.NEW_MOVIE_LIST)
    suspend fun movieList():BaseMovie
}