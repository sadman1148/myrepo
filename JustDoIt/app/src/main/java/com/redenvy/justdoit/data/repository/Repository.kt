package com.redenvy.justdoit.data.repository

import androidx.lifecycle.LiveData
import com.redenvy.justdoit.data.localDB.TodoDAO
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.data.network.APIService
import com.redenvy.justdoit.utils.Constants
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: APIService, private val todoDAO: TodoDAO) {

    suspend fun syncToLocalDbFromAPI(){
        val result = apiService.todoList()
        for (todoItem in result) {
            val timeInMili = SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse(todoItem.time).time
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

    suspend fun updateTodo(todoListItem: TodoListItem) {
        todoDAO.updateTodo(todoListItem)
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