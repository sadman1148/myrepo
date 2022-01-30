package com.redenvy.justdoit.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.google.gson.Gson
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class NotificationJobService : JobService() {
    @Inject
    lateinit var repo: Repository
    private lateinit var job: Job
    private lateinit var intent: Intent

    override fun onStartJob(jobParams: JobParameters): Boolean {
        intent = Intent(applicationContext, NotificationForegroundService::class.java)
        applicationContext.startService(intent)
        val todo: TodoListItem = Gson().fromJson(jobParams.extras.getString(Constants.BROADCAST_INTENT),CustomFunctions.getTodoType())

        job = CoroutineScope(Dispatchers.Default).launch {
            delay(1000*60*5)
            stopService(intent)
            repo.deleteTodoById(todo.id)
            val listofLatestTodo = repo.getLatestTodo()
            if(!listofLatestTodo.isEmpty()){
                applicationContext.sendBroadcast(Intent(Constants.INTENT_ACTION)
                    .putExtra(Constants.BUNDLE_NAME, Gson().toJson(listofLatestTodo.first()))
                )
            }
        }
        return false
    }

    override fun onStopJob(params : JobParameters?): Boolean {
        job.cancel()
        stopService(intent)
        jobFinished(params, false)
        return false
    }
}