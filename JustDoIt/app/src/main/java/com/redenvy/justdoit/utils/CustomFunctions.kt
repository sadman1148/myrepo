package com.redenvy.justdoit.utils

import android.content.Context
import android.content.Intent
import com.google.gson.reflect.TypeToken
import com.redenvy.justdoit.data.local.TodoListItem
import java.lang.reflect.Type

object CustomFunctions {

    fun getTodoType() : Type {
        return object : TypeToken<TodoListItem>() {}.type
    }

    fun sendBroadcastIntent(context: Context) {
        context.sendBroadcast(
            Intent(Constants.INTENT_ACTION).putExtra(Constants.SYNC_INTENT, true)
        )
    }

}