package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.databinding.FragmentAddTodoBinding
import com.redenvy.justdoit.ui.Activity.MainActivity
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.TimeToStringConverter
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.datetimepicker.showDateAndTimePicker
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTodoFragment : Fragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClicks()
    }

    private fun initOnClicks() {
        binding.apply {
            saveButton.setOnClickListener() {
                var timeAllow: Boolean = true
                var allow: Boolean = true
                var alert: String = ""
                if (binding.newTodoTime.text.toString().equals("")) {
                    alert += getString(R.string.time)
                    allow = false
                }
                if (binding.newTodoTitle.text.toString()
                        .equals("") || binding.newTodoTitle.text.toString().equals(" ")
                ) {
                    if (alert.equals("")) {
                        alert += getString(R.string.title_in_textform)
                    } else {
                        alert += getString(R.string.comma_title)
                    }
                    binding.newTodoTitle.requestFocus()
                    allow = false
                }
                if (binding.newSubtodo.text.toString()
                        .equals("") || binding.newSubtodo.text.toString().equals(" ")
                ) {
                    if (alert.equals("")) {
                        alert += getString(R.string.subtask_in_textform)
                    } else {
                        alert += getString(R.string.comma_subtask)
                    }
                    binding.newSubtodo.requestFocus()
                    allow = false
                }
                val time =
                    SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse(binding.newTodoTime.text.toString()).time
                if (time <= Calendar.getInstance().timeInMillis) {
                    timeAllow = false
                } else {
                    timeAllow = true
                }

                if (!allow) {
                    Toast.makeText(
                        context,
                        getString(R.string.please_add_intextform) + alert,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (allow) {
                        if (!timeAllow) {
                            Toast.makeText(
                                context,
                                getString(R.string.future_time_select),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val subTodoList: List<String> =
                                binding.newSubtodo.text.toString().split("\n")
                            val timeInMili =
                                SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN).parse(binding.newTodoTime.text.toString()).time
                            viewModel.insertNewTodo(
                                TodoListItem(
                                    UUID.randomUUID().toString(),
                                    timeInMili,
                                    binding.newTodoTitle.text.toString(),
                                    subTodoList
                                )
                            )
                            Toast.makeText(
                                context,
                                getString(R.string.todo_added),
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigateUp()
                        }
                    }
                }
            }
            newTodoTime.setOnClickListener() {
                timePicker()
            }
        }
    }

    private fun timePicker() {
        (requireActivity() as MainActivity).showDateAndTimePicker { date: Date ->
            binding.newTodoTime.setText(TimeToStringConverter.TimeToString(date.time))
        }
    }
}