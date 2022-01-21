package com.redenvy.roomdatabasetest

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redenvy.roomdatabasetest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private val TAG = "ami kotlin pari na"
    private lateinit var binder: ActivityMainBinding

    private val db: RoomDatabase by lazy {
        Room.databaseBuilder(
            this,
            DatabaseHelper::class.java,
            "users"
        ).fallbackToDestructiveMigration().build()
    }

    private val userDao: UserDao by lazy {
        (db as DatabaseHelper).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        binder.apply {
            btnRetrieve.setOnClickListener() {
                val users: List<User> = userDao.getUsers()
                if (users.isEmpty()) {
                    Log.e(TAG, "Database is Empty")
                } else {
                    Log.e(TAG, "${users[0].userID} - ${users[0].userName}")
                }
            }
            btnInsert.setOnClickListener() {
                val uname = binder.etName.text.toString()
                if (uname.equals("") or binder.etId.text.toString().equals("")){
                    Toast.makeText(applicationContext, "ID or Name field cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else{
                    val uid = parseInt(binder.etId.text.toString())
                    val newUser = User(uid,uname)
                    CoroutineScope(Dispatchers.IO).launch {
                        userDao.insert(newUser)
                    }
                    binder.etId.setText("")
                    binder.etName.setText("")
                }
            }
        }
    }
}