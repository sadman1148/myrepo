package com.redenvy.justdoit.data.repository

import androidx.lifecycle.LiveData
import com.redenvy.justdoit.data.localDB.TodoDAO
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.data.network.APIService
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: APIService, private val todoDAO: TodoDAO) {

    suspend fun syncToLocalDbFromAPI(){
        val result = apiService.todoList()
        for (todoItem in result) {
            val timeInMili = SimpleDateFormat("yyyy-MM-dd HH:mm a").parse(todoItem.time).time
            if (timeInMili <= Calendar.getInstance().timeInMillis)
                continue
            todoDAO.insertData(TodoListItem(
                todoItem.id,
                timeInMili,
                todoItem.title,
                todoItem.todo)
            )
        }
    }

    fun getData() : LiveData<List<TodoListItem>>{
        return todoDAO.getData()
    }

    suspend fun getTodoById(todoId: String) : TodoListItem{
        return todoDAO.getTodoById(todoId)
    }

    suspend fun insertData(todoListItem : TodoListItem) {
        todoDAO.insertData(todoListItem)
    }

    suspend fun delete(todoListItem : TodoListItem) {
        todoDAO.delete(todoListItem)
    }

    suspend fun deleteAll() {
        todoDAO.deleteAll()
    }

}