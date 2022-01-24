package com.redenvy.justdoit.data.repository

import androidx.lifecycle.LiveData
import com.redenvy.justdoit.data.model.TodoList
import com.redenvy.justdoit.data.network.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null

    fun getActivity(): LiveData<TodoList>{
        job = Job()
        return object : LiveData<TodoList>(){
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val activity = RetrofitBuilder.apiService.todoList()
                        withContext(Main){
                            value = activity
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}