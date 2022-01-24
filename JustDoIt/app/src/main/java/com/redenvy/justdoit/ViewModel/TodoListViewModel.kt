package com.redenvy.justdoit.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redenvy.justdoit.data.model.TodoList
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repo:Repository) : ViewModel() {
    private val _todoList = MutableLiveData<DataState<TodoList>>()
    val todoLists: LiveData<DataState<TodoList>> get() = _todoList

    fun getTodo(){
        viewModelScope.launch {
            repo.todoList().onEach {
                _todoList.value = it
            }.launchIn(viewModelScope)
        }
    }
}