package com.redenvy.justdoit.utils

import android.content.Context
import android.content.Intent
import com.google.gson.reflect.TypeToken
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.ui.Activity.MainActivity
import jhonatan.sabadi.datetimepicker.showDateAndTimePicker
import java.lang.reflect.Type
import java.util.*

object CustomFunctions {

    /**
     * this custom function returns the datatype for TodoListItem
     */
    fun getTodoType() : Type {
        return object : TypeToken<TodoListItem>() {}.type
    }

    /**
     * this custom function checks the difference between two lists and returns true if they are same
     */
    fun isEqual(first: List<String>, second: List<String>): Boolean {
        if (first.size != second.size) {
            return false
        }
        first.forEachIndexed { index, value ->
            if (second[index] != value) {
                return false
            }
        }
        return true
    }
}