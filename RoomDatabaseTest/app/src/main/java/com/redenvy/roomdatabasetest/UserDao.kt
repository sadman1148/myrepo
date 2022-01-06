package com.redenvy.roomdatabasetest

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(vararg user: User)

    @Query("SELECT * FROM users")
    fun getUsers():List<User>

    @Delete
    fun delete(user: User)
}