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

    fun initOnClicks() {
        binding.apply {
            updateButton.setOnClickListener() {
                val editedList: List<String> = binding.editSubtodo.text.toString().split("\n").dropLastWhile { it == "" }
                val datetime =
                    SimpleDateFormat(Constants.FORMAT_TO_STRING_PATTERN).format(todo.time)

                // this if block checks if all the data are still same (User has not changed ANY data)
                if (
                    todo.title.equals(binding.editTodoTitle.text.toString()) &&
                    datetime.equals(binding.newTodoTime.text.toString()) &&
                    CustomFunctions.isEqual(editedList, todo.todo)
                ) {
                    Toast.makeText(context, getString(R.string.no_new_changes), Toast.LENGTH_SHORT)
                        .show()
                }

                // this else block will execute if user has made changes
                else {

                    // this if block checks if the text fields are empty
                    if (
                        binding.editTodoTitle.text.toString().equals("") || binding.editSubtodo.text.toString().equals("")
                    ) {
                        Toast.makeText(
                            context,
                            getString(R.string.no_data_in_textfield),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    // this else block will execute if user filled out the text fields
                    else {

                        // this if block checks if the Time has been changed by the user or not
                        if (datetime.equals(binding.newTodoTime.text.toString())) {

                            // if the time is unchanged, then the user will update with new texts but old time
                            setData(
                                todo.id,
                                todo.time,
                                binding.editTodoTitle.text.toString(),
                                editedList
                            )
                            findNavController().navigateUp()
                        }

                        // this else block will execute when user has set a new time
                        else {
                            val time = SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse(binding.newTodoTime.text.toString()).time

                            // this if block checks if the time is valid
                            if(time<Calendar.getInstance().timeInMillis){
                                Toast.makeText(context, "Please enter a future time", Toast.LENGTH_SHORT).show()
                            }

                            // this else block will execute if time is set as a future time
                            else{
                                setData(
                                    todo.id,
                                    time,
                                    binding.editTodoTitle.text.toString(),
                                    editedList
                                )
                                Toast.makeText(
                                    context,
                                    getString(R.string.todo_updated),
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigateUp()
                            }
                        }
                    }
                }
            }
            newTodoTime.setOnClickListener() {
                timePicker()
            }
        }
    }

    /**
     * This function sets data to a TodoListItem initializer and calls the update function from viewmodel
     */
    private fun setData(id: String, time: Long, title: String, list: List<String>) {
        viewModel.updateTodo(
            TodoListItem(id, time, title, list)
        )
    }

    /**
     * This function shows the time and date picker
     */
    private fun timePicker() {
        (requireActivity() as MainActivity).showDateAndTimePicker { date: Date ->
            binding.newTodoTime.setText(TimeToStringConverter.TimeToString(date.time))
        }
    }
}