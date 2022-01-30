package com.redenvy.justdoit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoListItem(
    @PrimaryKey val id: String,
    val time: Long,
    val title: String,
    val todo: List<String>
)