package com.redenvy.roomdatabasetest

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.redenvy.roomdatabasetest.databinding.ActivityMainBinding
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private val TAG = "ami kotlin pari na"
    private lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        val db = Room.databaseBuilder(applicationContext, DatabaseHelper::class.java, "users").build()
        val userDao = db.userDao()
        binder.apply {
            btnRetrieve.setOnClickListener(){
                val users: List<User> = userDao.getUsers()
                Log.e(TAG, ""+ users[0].userID +" "+ users[0].userName)
            }
            btnInsert.setOnClickListener(){
                val newUser = User(parseInt(binder.etId.text.toString()),binder.etName.text.toString())
                db.userDao().insert(newUser)
            }
        }
    }
}