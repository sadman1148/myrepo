package com.example.to_dolist.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_dolist.R
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.databinding.FragmentAddBinding
import com.example.to_dolist.ui.MainActivity
import com.example.to_dolist.utils.TimeToStringConverter
import com.example.to_dolist.viewModel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.datetimepicker.showDateAndTimePicker
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: TodoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            dateTimeBtn.setOnClickListener() {
                dateTimePicker()
            }
            addTaskBtn.setOnClickListener {
                var allow: Boolean = true
                var message: String = ""
                if (binding.addTaskTitle.text.toString().equals("")) {
                    //Toast.makeText(context, "Please add a title", Toast.LENGTH_SHORT).show()
                    message = "title"
                    allow = false
                    binding.addTaskTitle.requestFocus()
                }
                if (binding.taskDateTime.text.toString().equals("")) {
                    //Toast.makeText(context, "Please select time", Toast.LENGTH_SHORT).show()
                    if (message.equals("")) {
                        message = message + "time"
                    } else {
                        message = message + ",time"
                    }
                    allow = false
                }
                if (binding.subTask.text.toString().equals("")) {
                    //Toast.makeText(context, "Please add a subtask", Toast.LENGTH_SHORT).show()
                    if (message.equals("")) {
                        message = message + "subtask"
                    } else {
                        message = message + " and subtask"
                    }
                    allow = false
                    binding.subTask.requestFocus()
                }

                if (allow) {
                    saveUserTodoToDatabase()
                    findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Please add " + message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun saveUserTodoToDatabase() {
        val subTasks: String = binding.subTask.text.toString()
        val subTaskArray: List<String> = subTasks.split("\n")
        val timeInMili =
            SimpleDateFormat("yyyy-MM-dd hh:mm aa").parse(binding.taskDateTime.text.toString()).time
        viewModel.insertTodo(
            TodoListItem(
                UUID.randomUUID().toString(),
                timeInMili,
                binding.addTaskTitle.text.toString(),
                subTaskArray
            )
        )
        Toast.makeText(activity,
            "Added successfully",
            Toast.LENGTH_SHORT).show();
    }

    private fun dateTimePicker() {
        (requireActivity() as MainActivity).showDateAndTimePicker { date: Date ->
            binding.taskDateTime.setText(TimeToStringConverter.TimeToString(date.time))
        }
    }
}