package com.redenvy.justdoit.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.npoint.io/"
    const val EXTENSION_URL = "154f32b1a6eea7ef6372"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService : APIService by lazy {
        retrofitBuilder
            .build()
            .create(APIService::class.java)
    }
}