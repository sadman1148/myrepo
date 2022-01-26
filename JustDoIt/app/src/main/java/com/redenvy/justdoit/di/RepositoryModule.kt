package com.redenvy.justdoit.di

import com.redenvy.justdoit.data.localDB.TodoDAO
import com.redenvy.justdoit.data.network.APIService
import com.redenvy.justdoit.data.repository.Repository
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
    fun provideTodoRepository(apiService: APIService, todoDAO : TodoDAO): Repository{
        return Repository(apiService,todoDAO)
    }
}