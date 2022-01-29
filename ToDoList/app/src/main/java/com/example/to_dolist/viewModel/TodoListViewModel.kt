package com.example.to_dolist.viewModel

import androidx.lifecycle.*
import com.example.to_dolist.data.model.TodoArrayList
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.data.repository.Repository
import com.example.to_dolist.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repo: Repository) : ViewModel(),
    LifecycleObserver {
    private val _todoList = MutableLiveData<DataState<TodoArrayList>>()
    val todoArrayLists: LiveData<DataState<TodoArrayList>> get() = _todoList
    lateinit var allTodoArrayList: MutableLiveData<List<TodoArrayList>>
    lateinit var to: TodoListItem
    val getAllTodoData: LiveData<List<TodoListItem>> get() = repo.getAllData()

    fun syncToLocalDB() {
        viewModelScope.launch(Dispatchers.Default) {
            repo.insertTodoFromApiToLocalDB()
        }
    }

    fun insertTodo(todoList: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertTodoList(todoList)
        }

    }

    fun deleteTodoList(todoList: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodoList(todoList)
        }

    }
    fun updateTodoList(todoList: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodoList(todoList)
        }

    }

}