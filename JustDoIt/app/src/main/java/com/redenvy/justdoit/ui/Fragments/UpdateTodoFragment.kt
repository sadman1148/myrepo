package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.data.localDB.TodoListItem
import com.redenvy.justdoit.databinding.FragmentUpdateTodoBinding
import com.redenvy.justdoit.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

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
        //initOnClicks()
    }

    fun initView(){
        arguments?.getString(Constants.BUNDLE_NAME)?.let { receivedTodo = it }
        val type = object : TypeToken<TodoListItem>() {}.type
        todo = Gson().fromJson(receivedTodo, type)
//        binding.editTodoTitle.setText(todo.title)
//        binding.newTodoTime.setText(todo.time)
        val subTaskArray : List<String>
    }

    fun initOnClicks(){
        binding.apply {
//            deleteButton.setOnClickListener(){
//
//                )
//            }
        }
    }

}