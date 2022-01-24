package com.redenvy.justdoit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redenvy.justdoit.data.model.TodoList

class viewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoList>>()
    val todoLists: LiveData<List<TodoList>> get() = _todoList

    fun addTodo(list: List<TodoList>){
        _todoList.postValue(list)
    }
}