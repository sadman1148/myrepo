package com.redenvy.justdoit.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import timber.log.Timber
import java.util.*

class Broadcaster : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val jsonTodoWithTodoSubtask = intent?.getStringExtra(Constants.BUNDLE_NAME)
        if (jsonTodoWithTodoSubtask != null) {
            val todo: TodoListItem =
                Gson().fromJson(jsonTodoWithTodoSubtask, CustomFunctions.getTodoType())
            scheduleNotificationJob(todo, context!!)
        }

        if (intent?.getBooleanExtra(Constants.SYNC_INTENT, false) == true) {
            scheduleSyncJob(1000 * 60 * 60 * 12, context!!)
        }
    }

    /**
     * Schedules the notification
     */
    private fun scheduleNotificationJob(todo: TodoListItem, context: Context) {
        cancelNotificationJob(context)
        val timeDifference = todo.time!! - Calendar.getInstance().time.time - 1000 * 60 * 1
        val bundle = PersistableBundle()
        bundle.putString(Constants.BROADCAST_INTENT, Gson().toJson(todo))
        val componentName = ComponentName(context, NotificationJobService::class.java)
        val jobInfo = JobInfo.Builder(17101403, componentName)
            .setPersisted(true)
            .setMinimumLatency(timeDifference)
            .setExtras(bundle)
            .build()
        val jobScheduler =
            context.getSystemService(AppCompatActivity.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }

    private fun cancelNotificationJob(context: Context) {
        val jobScheduler =
            context.getSystemService(AppCompatActivity.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(17101403)
    }

    private fun scheduleSyncJob(time: Long, context: Context) {
        cancelSyncJob(context)
        val bundle = PersistableBundle()
        val componentName = ComponentName(
            context,
            SyncJobService::class.java
        )
        val jobInfo = JobInfo.Builder(11432, componentName)
            .setPersisted(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(time)
            .setMinimumLatency(1000 * 10)
            .setExtras(bundle)
            .build()
        val jobScheduler =
            context.getSystemService(AppCompatActivity.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)


    }

    private fun cancelSyncJob(context: Context) {
        val jobScheduler =
            context.getSystemService(AppCompatActivity.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(17101403)
    }
}