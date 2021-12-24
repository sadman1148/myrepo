package com.example.sometask.data.remote

import retrofit2.http.GET

interface remoteAPI{
    @GET("www.ggez.com")
    fun movieList()
}