package com.redenvy.justdoit.di

import com.redenvy.justdoit.data.network.APIService
import com.redenvy.justdoit.data.repository.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    fun provideTodoRepository(apiService: APIService): Repository{
        return Repository(apiService)
    }
}