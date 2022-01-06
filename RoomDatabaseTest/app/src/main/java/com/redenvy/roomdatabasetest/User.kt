package com.redenvy.roomdatabasetest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "uid")
    val userID: Number,
    @ColumnInfo(name = "uname")
    val userName: String
)