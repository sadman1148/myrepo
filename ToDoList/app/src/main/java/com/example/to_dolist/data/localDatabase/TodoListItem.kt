package com.example.to_dolist.data.localDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todoList")
data class TodoListItem(
    @PrimaryKey val id: String,
    val time: Long,
    val title: String,
    val todo: List<String>
)