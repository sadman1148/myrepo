package com.example.to_dolist.data.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodoList(todoList: TodoListItem)

    @Update
    suspend fun updateTodoList(todoList: TodoListItem)

    @Delete
    suspend fun deleteTodoList(todoList: TodoListItem)

    @Query("SELECT * FROM todoList order by time ASC")
    fun getTodoList(): LiveData<List<TodoListItem>>
}