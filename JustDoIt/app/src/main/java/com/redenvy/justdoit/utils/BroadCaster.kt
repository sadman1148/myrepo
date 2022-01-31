package com.redenvy.justdoit.utils

import android.content.Context
import android.content.Intent
import com.redenvy.justdoit.utils.services.MyBroadcastReceiver
import timber.log.Timber

object BroadCaster {
    fun sendNewBroadcast(context: Context) {
        val intent = Intent(context, MyBroadcastReceiver::class.java)
        Timber.e("Broadcaster called")
        intent.action = Constants.DATA_UPDATE_INTENT
        context.sendBroadcast(intent)
    }
}