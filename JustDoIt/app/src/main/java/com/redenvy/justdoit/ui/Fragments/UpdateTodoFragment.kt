package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.databinding.FragmentUpdateTodoBinding
import com.redenvy.justdoit.ui.Activity.MainActivity
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import com.redenvy.justdoit.utils.TimeToStringConverter
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.datetimepicker.showDateAndTimePicker
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class UpdateTodoFragment : Fragment() {
    private lateinit var receivedTodo: String
    private lateinit var todo: TodoListItem
    private lateinit var binding: FragmentUpdateTodoBinding
    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentUpdateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClicks()
    }

    fun initView() {
        arguments?.getString(Constants.BUNDLE_NAME)?.let { receivedTodo = it }
        todo = Gson().fromJson(receivedTodo, CustomFunctions.getTodoType())
        binding.editTodoTitle.setText(todo.title)
        val datetime = SimpleDateFormat(Constants.FORMAT_TO_STRING_PATTERN).format(todo.time)
        binding.newTodoTime.setText(datetime)
        val subTaskString = todo.todo.joinToString("\n")
        binding.editSubtodo.setText(subTaskString)
    }

    fun isEqual(first: List<String>, second: List<String>): Boolean {
        if (first.size != second.size) {
            return false
        }
        first.forEachIndexed { index, value ->
            if (second[index] != value) {
                return false
            }
        }
        return true
    }

    fun initOnClicks() {
        binding.apply {
            updateButton.setOnClickListener() {
                val editedList: List<String> = binding.editSubtodo.text.toString().split("\n")
                val datetime =
                    SimpleDateFormat(Constants.FORMAT_TO_STRING_PATTERN).format(todo.time)
                if (
                    todo.title.equals(binding.editTodoTitle.text.toString()) &&
                    datetime.equals(binding.newTodoTime.text.toString()) &&
                    isEqual(editedList, todo.todo)
                ) {
                    Toast.makeText(context, getString(R.string.no_new_changes), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (binding.editTodoTitle.text.toString()
                            .equals("") || binding.editTodoTitle.text.toString().equals(" ") &&
                        binding.editSubtodo.text.toString()
                            .equals("") || binding.editSubtodo.text.toString().equals(" ")
                    ) {
                        Toast.makeText(
                            context,
                            getString(R.string.no_data_in_textfield),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (datetime.equals(binding.newTodoTime.text.toString())) {
                            setData(
                                todo.id,
                                todo.time,
                                binding.editTodoTitle.text.toString(),
                                editedList
                            )
                        } else {
                            setData(
                                todo.id,
                                SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse(binding.newTodoTime.text.toString()).time,
                                binding.editTodoTitle.text.toString(),
                                editedList
                            )
                        }
                        Toast.makeText(
                            context,
                            getString(R.string.todo_updated),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    }
                }
            }
            newTodoTime.setOnClickListener() {
                timePicker()
            }
        }
    }

    private fun setData(id: String, time: Long, title: String, list: List<String>) {
        viewModel.updateTodo(
            TodoListItem(id, time, title, list)
        )
    }

    private fun timePicker() {
        (requireActivity() as MainActivity).showDateAndTimePicker { date: Date ->
            binding.newTodoTime.setText(TimeToStringConverter.TimeToString(date.time))
        }
    }
}