package com.redenvy.justdoit.ui.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.redenvy.justdoit.R
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.databinding.ActivityMainBinding
import com.redenvy.justdoit.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var tempTodoHolder : TodoListItem = TodoListItem(
        "dummy id",
        SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse("1998-01-11 01:00 PM").time,
        "dummy title",
        listOf("dummy1","dummy2")
    )
    fun sendTodoToFrag() : TodoListItem = tempTodoHolder
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}