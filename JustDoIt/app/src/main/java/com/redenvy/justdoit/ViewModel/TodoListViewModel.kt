package com.redenvy.justdoit.ViewModel

import androidx.lifecycle.*
import com.redenvy.justdoit.data.Remote.TodoList
import com.redenvy.justdoit.data.local.TodoListItem
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

    private val _isGetAllowed = MutableLiveData<Boolean>()
    val isGetAllowed: LiveData<Boolean> get() = _isGetAllowed

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

    fun getTodoById(id : String) {
        _isGetAllowed.postValue(false)
        viewModelScope.launch (Dispatchers.Default) {
            tempTodoListItem = repo.getTodoById(id)
        }.invokeOnCompletion {
            _isGetAllowed.postValue(true)
        }
    }

    fun deleteTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.delete(todoListItem)
        }
    }

    fun deleteTodoById(id : String){
        viewModelScope.launch (Dispatchers.Default) {
            repo.deleteTodoById(id)
        }
    }

    fun updateTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.updateTodo(todoListItem)
        }
    }
}