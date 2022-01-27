package com.redenvy.justdoit.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.utils.DataState


@Dao
interface TodoDAO {

    @Query("SELECT * FROM todolistitem")
    fun getData() : LiveData<List<TodoListItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(vararg todoListItem: TodoListItem)

    @Delete
    fun delete(todoListItem: TodoListItem)

    @Query("DELETE FROM todolistitem")
    fun deleteAll()
}