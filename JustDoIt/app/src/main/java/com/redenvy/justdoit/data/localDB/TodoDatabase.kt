package com.redenvy.justdoit.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.utils.TodoConverter

@Database(entities = [TodoListItem::class], version = 1)
@TypeConverters(TodoConverter::class)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDAO() : TodoDAO
}