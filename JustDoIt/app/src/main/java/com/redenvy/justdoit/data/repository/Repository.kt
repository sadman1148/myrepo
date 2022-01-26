package com.redenvy.justdoit.data.repository

import com.redenvy.justdoit.data.localDB.TodoDAO
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.data.network.APIService
import com.redenvy.justdoit.utils.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: APIService,
    private val todoDAO: TodoDAO) {

    suspend fun todoList() = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.todoList()
            emit(DataState.Success(result))
        }
        catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getData(){
        todoDAO.getData()
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