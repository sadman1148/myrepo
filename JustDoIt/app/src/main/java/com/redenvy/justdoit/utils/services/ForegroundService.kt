package com.redenvy.justdoit.utils.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.gson.Gson
import com.redenvy.justdoit.R
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions

class ForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val todoData = intent?.extras?.get(Constants.TODO_ITEM_KEY) as String
        val todo : TodoListItem = Gson().fromJson(todoData,CustomFunctions.getTodoType())
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Most of the code in the services package are boiler plate code

        // Building the notification channel here

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationChannel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            mNotificationChannel.description = Constants.NOTIFICATION_CHANNEL_DESC
            mNotificationChannel.enableLights(true)
            mNotificationChannel.lightColor = Color.WHITE
            mNotificationManager.createNotificationChannel(mNotificationChannel)
            // bypassing Do not disturb moode
            mNotificationChannel.canBypassDnd()
        }

        val notification =
            NotificationCompat.Builder(applicationContext, Constants.NOTIFICATION_CHANNEL_ID)
                .setContentTitle(todo.title)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(todo.todo.joinToString("\n")))
                .setSmallIcon(R.drawable.ic_just_do_it)
                .build()
        startForeground(Constants.FOREGROUND_SERVICE_ID, notification)

        // making notification stick to the notification bar until time is up
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}