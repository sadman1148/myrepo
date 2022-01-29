package com.example.to_dolist.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.to_dolist.R
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.databinding.FragmentUpdateBinding
import com.example.to_dolist.ui.MainActivity
import com.example.to_dolist.utils.Constants
import com.example.to_dolist.utils.TimeToStringConverter
import com.example.to_dolist.viewModel.TodoListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.datetimepicker.showDateAndTimePicker
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var data: String
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var todo: TodoListItem
    private val viewModel: TodoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTodoInDetails()
        initOnClick()
    }

    private fun updateTodoInDetails() {
        arguments?.getString(Constants.DATA_FROM_DETAILS_TO_UPDATE)?.let { data = it }
        val type = object : TypeToken<TodoListItem>() {}.type
        todo = Gson().fromJson(data, type)
        binding.updateTaskTitle.setText(todo.title)
        val datetime = SimpleDateFormat("EE dd MMM yyyy hh:mm a").format(todo.time)
        binding.updateTaskDateTime.setText(datetime)
        binding.updateSubTask.setText(todo.todo.joinToString("\n"))

    }

    private fun initOnClick() {
        binding.apply {
            updateDateTimeBtn.setOnClickListener {
                dateTimePicker()
            }
            updateTaskBtn.setOnClickListener {
                val title: String = binding.updateTaskTitle.text.toString()
                val subTasks: String = binding.updateSubTask.text.toString()
                val subTaskArray: List<String> = subTasks.split("\n")
                val datetime = SimpleDateFormat("EE dd MMM yyyy hh:mm a").format(todo.time)
                if (!binding.updateTaskDateTime.text.toString().equals(datetime)) {
                    val timeInMili =
                        SimpleDateFormat("yyyy-MM-dd HH:mm a").parse(binding.updateTaskDateTime.text.toString()).time
                    viewModel.updateTodoList(
                        TodoListItem(
                            todo.id,
                            timeInMili,
                            binding.updateTaskTitle.text.toString(),
                            subTaskArray
                        )
                    )
                } else {
                    viewModel.updateTodoList(
                        TodoListItem(
                            todo.id,
                            todo.time,
                            binding.updateTaskTitle.text.toString(),
                            subTaskArray
                        )
                    )
                }
                val data = bundleOf(Constants.PACKAGE_NAME to Gson().toJson(todo))
                findNavController().navigate(R.id.action_updateFragment_to_detailsFragment,data)

            }
        }
    }

    //    fun isEqualArray(first: List<String>, second: List<String>): Boolean {
//        if (first.size != second.size) {
//            return false
//        }
//        first.forEachIndexed { index, value -> if (second[index] != value) { return false } }
//        return true
//    }
    private fun dateTimePicker() {
        (requireActivity() as MainActivity).showDateAndTimePicker { date: Date ->
            binding.updateTaskDateTime.setText(TimeToStringConverter.TimeToString(date.time))
        }
    }
}