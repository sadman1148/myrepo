package com.redenvy.justdoit.ViewModel

import androidx.lifecycle.*
import com.redenvy.justdoit.data.Remote.TodoList
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repo:Repository) : ViewModel(),LifecycleObserver {

    // This will be observed for local db changes
    val localTodoList: LiveData<List<TodoListItem>> get() = repo.getData()

    // This will replicate an "On Completion" effect
    private val _isGetAllowed = MutableLiveData<Boolean>()
    val isGetAllowed: LiveData<Boolean> get() = _isGetAllowed

    lateinit var tempTodoListItem : TodoListItem

    /**
     * this function syncs the local database with online cloud data
     */
    fun syncToLocalDbFromAPI() {
        viewModelScope.launch (Dispatchers.Default) {
            repo.syncToLocalDbFromAPI()
        }
    }

    /**
     * this function inserts new TodoListItems in the local database
     */
    fun insertNewTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.insertData(todoListItem)
        }
    }

    /**
     * assigns a TodoListItem to a late inited variable but only when it is allowed
     */
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

    fun updateTodo(todoListItem : TodoListItem){
        viewModelScope.launch (Dispatchers.Default) {
            repo.updateTodo(todoListItem)
        }
    }
}