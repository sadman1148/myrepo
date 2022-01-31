package com.redenvy.justdoit.utils.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PersistableBundle
import com.google.gson.Gson
import com.redenvy.justdoit.data.repository.Repository
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MyBroadcastReceiver : BroadcastReceiver() {
    @Inject
    lateinit var repo: Repository

    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.Default).launch {

            //  cancelling last scheduled job
            stopJob(context)

            // returning latest TodoListItem in  a list
            val todolist = repo.getLatestTodo()

            // checking if there is any TodoListItem exists
            if (!todolist.isEmpty()) {

                // calculating estimated time
                val eta = todolist.first().time - Calendar.getInstance().time.time

                startJob(context, eta - Constants.FIVE_MIN_DELAY_TIME, Gson().toJson(todolist.first(), CustomFunctions.getTodoType()))
            }
        }
    }

    private fun stopJob(context: Context) {
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(Constants.NEXT_NOTIFICATION_JOB_ID)
    }

    private fun startJob(context: Context, time: Long, todoData: String) {
        val componentName = ComponentName(context, NotificationJob::class.java)
        val persistentBundle = PersistableBundle()
        persistentBundle.putString(Constants.BUNDLE_NAME, todoData)
        val jobInfo = JobInfo.Builder(Constants.NEXT_NOTIFICATION_JOB_ID, componentName)
            .setMinimumLatency(time)
            .setExtras(persistentBundle)
            .setPersisted(true)
            .build()
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val result = jobScheduler.schedule(jobInfo)
        if (result == JobScheduler.RESULT_SUCCESS) {
//            Timber.e("Notification job scheduled for $jsonItem")
        }
    }
}