package com.redenvy.justdoit.data.Remote

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