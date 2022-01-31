package com.redenvy.justdoit.utils.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import com.google.gson.Gson
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.BroadCaster
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NotificationJob : JobService() {

    @Inject
    lateinit var repo: Repository
    lateinit var job: Job
    lateinit var serviceIntent: Intent

    override fun onStartJob(params: JobParameters): Boolean {
        val jsonItem = params.extras.get(Constants.BUNDLE_NAME) as String
        executeJob(Gson().fromJson(jsonItem,CustomFunctions.getTodoType()))
        return true
    }

    private fun executeJob(todo : TodoListItem) {
        serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra(Constants.TODO_ITEM_KEY, Gson().toJson(todo))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        job = CoroutineScope(Dispatchers.Default).launch {
            // Delaytime before deleting the TodoListItem
            delay(Constants.FIVE_MIN_DELAY_TIME)
            stopService(serviceIntent)
            repo.deleteTodoById(todo.id)
            BroadCaster.sendNewBroadcast(
                applicationContext,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        stopService(serviceIntent)
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return false
    }
}