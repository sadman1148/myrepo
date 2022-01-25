package com.redenvy.justdoit.data.model


import com.google.gson.annotations.SerializedName

data class TodoListItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("todo")
    val todo: List<String>
)