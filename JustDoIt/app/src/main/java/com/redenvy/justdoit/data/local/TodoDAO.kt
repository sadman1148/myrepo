package com.redenvy.justdoit.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todolistitem ORDER BY time ASC")
    fun getData() : LiveData<List<TodoListItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(vararg todoListItem : TodoListItem)

    @Delete
    fun delete(todoListItem : TodoListItem)

    @Query("DELETE FROM todolistitem")
    fun deleteAll()

    @Query("DELETE FROM todolistitem WHERE id LIKE :id")
    fun deleteTodoById(id : String)

    @Update
    fun updateTodo(todoListItem : TodoListItem)

    @Query("SELECT * FROM todolistitem WHERE id LIKE :id")
    fun getTodoById(id:String) : TodoListItem

    @Transaction
    @Query("SELECT * FROM todolistitem ORDER BY time ASC LIMIT 1")
    fun getLatestTodo() : List<TodoListItem>

}