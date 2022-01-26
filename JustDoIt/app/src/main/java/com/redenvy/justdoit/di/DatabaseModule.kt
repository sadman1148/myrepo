package com.redenvy.justdoit.di

import android.content.Context
import androidx.room.Room
import com.redenvy.justdoit.data.localDB.TodoDatabase
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.TodoConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            Constants.LOCAL_DB_NAME)
        .fallbackToDestructiveMigration()
        .addTypeConverter(TodoConverter())
        .build()

    @Singleton
    @Provides
    fun provideTodoDAO(db:TodoDatabase) = db.todoDAO()
}