package com.redenvy.justdoit.ViewModel

import androidx.lifecycle.*
import com.redenvy.justdoit.data.model.TodoList
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repo:Repository) : ViewModel(),LifecycleObserver {
    private val _todoList = MutableLiveData<DataState<TodoList>>()
    val todoLists: LiveData<DataState<TodoList>> get() = _todoList

    fun insertToLocalDbFromAPI() {
        viewModelScope.launch {
            repo.todoList().onEach { it ->
                when (it) {
                    is DataState.Success -> {
                        it.data.forEach() {
                            val todoData = TodoListItem(it.id, it.time, it.title, it.todo)
                            extraScope(todoData)
                        }
                        _todoList.value = it
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun extraScope(todoData : TodoListItem){
        viewModelScope.launch (Dispatchers.IO) {
            repo.insertData(todoData)
        }
    }
}