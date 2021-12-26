package com.example.sometask.di

import com.example.sometask.data.MovieRepo
import com.example.sometask.data.remote.remoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(remoteAPI: remoteAPI):MovieRepo{
        return MovieRepo(remoteAPI)
    }
}