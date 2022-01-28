package com.redenvy.justdoit.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todolistitem ORDER BY time ASC")
    fun getData() : LiveData<List<TodoListItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(vararg todoListItem: TodoListItem)

    @Delete
    fun delete(todoListItem: TodoListItem)

    @Query("DELETE FROM todolistitem")
    fun deleteAll()

    @Update
    fun updateTodo(todoListItem: TodoListItem)

}