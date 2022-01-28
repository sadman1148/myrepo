package com.redenvy.justdoit.ViewModel

import androidx.lifecycle.*
import com.redenvy.justdoit.data.model.TodoList
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repo:Repository) : ViewModel(),LifecycleObserver {
    private val _todoList = MutableLiveData<DataState<TodoList>>()
    val todoLists: LiveData<DataState<TodoList>> get() = _todoList

    val localTodoList: LiveData<List<TodoListItem>> get() = repo.getData()

    lateinit var tempTodoListItem : TodoListItem

    fun syncToLocalDbFromAPI() {
        viewModelScope.launch (Dispatchers.Default) {
            repo.syncToLocalDbFromAPI()
        }
    }

    fun insertNewTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.insertData(todoListItem)
        }
    }

    fun deleteTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.delete(todoListItem)
        }
    }

    fun updateTodo(todoListItem: TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.updateTodo(todoListItem)
        }
    }

}