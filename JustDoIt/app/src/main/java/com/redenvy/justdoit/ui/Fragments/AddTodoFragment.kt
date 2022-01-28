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
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.databinding.FragmentAddTodoBinding
import com.redenvy.justdoit.ui.Activity.MainActivity
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
            saveButton.setOnClickListener(){
                var allow : Boolean = true
                var alert : String = ""
                if(binding.newTodoTime.text.toString().equals("")){
                    alert += "Time"
                    allow = false
                }
                if (binding.newTodoTitle.text.toString().equals("")){
                    if (alert.equals("")){
                        alert += "Title"
                    }else{
                        alert += ", Title"
                    }
                    binding.newTodoTitle.requestFocus()
                    allow = false
                }
                if (binding.newSubtodo.text.toString().equals("")){
                    if (alert.equals("")){
                        alert += "Subtask"
                    }else{
                        alert += ", Subtask"
                    }
                    binding.newSubtodo.requestFocus()
                    allow = false
                }
                if (!allow){
                    Toast.makeText(context, "Please add "+alert, Toast.LENGTH_SHORT).show()
                }
                if (allow){
                    val subTodoList : List<String> = binding.newSubtodo.text.toString().split("\n")
                    val timeInMili = SimpleDateFormat("yyyy-MM-dd HH:mm a").parse(binding.newTodoTime.text.toString()).time
                    viewModel.insertNewTodo(
                        TodoListItem(
                            UUID.randomUUID().toString(),
                            timeInMili,
                            binding.newTodoTitle.text.toString(),
                            subTodoList
                        )
                    )
                    Toast.makeText(context, "Add Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addTodoFragment_to_mainFragment)
                }
            }
            addTimeButton.setOnClickListener(){
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