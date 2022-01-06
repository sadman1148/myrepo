package com.redenvy.roomdatabasetest

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.room.Room
import com.redenvy.roomdatabasetest.databinding.ActivityMainBinding
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private val TAG = "ami kotlin pari na"
    private val db:DatabaseHelper by lazy { Room.databaseBuilder(this,
        DatabaseHelper::class.java, "users").allowMainThreadQueries().build() }
    private lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        val userDao = db.userDao()
        binder.apply {
            btnRetrieve.setOnClickListener(){
                val users: List<User> = userDao.getUsers()
                if(users.isEmpty()){
                    Log.e(TAG, "Database is Empty")
                }else{
                    Log.e(TAG, "${users[0].userID} - ${users[0].userName}")
                }
            }
            btnInsert.setOnClickListener(){
                val newUser = User(parseInt(binder.etId.text.toString()),binder.etName.text.toString())
                db.userDao().insert(newUser)
            }
        }
    }
}