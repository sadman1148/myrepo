package com.example.sometask.di

import com.example.sometask.data.apiURL
import com.example.sometask.data.remote.remoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(apiURL.BASE_URL).build()
    }

    fun provideApiService(retrofit: Retrofit):remoteAPI{
        return retrofit.create(remoteAPI::class.java)
    }
}