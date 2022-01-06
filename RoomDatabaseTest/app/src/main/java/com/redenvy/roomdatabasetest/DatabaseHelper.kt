package com.redenvy.roomdatabasetest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun userDao(): UserDao
}