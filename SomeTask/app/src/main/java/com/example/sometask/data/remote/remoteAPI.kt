package com.example.sometask.data.remote

import com.example.sometask.data.apiURL
import retrofit2.http.GET

interface remoteAPI{
    @GET(apiURL.NEW_MOVIE_LIST)
    fun movieList()
}