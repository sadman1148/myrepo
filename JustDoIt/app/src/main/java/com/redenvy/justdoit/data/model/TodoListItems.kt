package com.redenvy.justdoit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TodoListItems(
    @SerializedName("id")
    val id: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("todo")
    val todo: List<String>
)