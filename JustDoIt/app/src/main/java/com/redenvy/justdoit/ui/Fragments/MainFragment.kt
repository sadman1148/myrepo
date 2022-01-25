package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.FragmentMainBinding
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        //initViewModel()
        initOnClicks()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initViewModel()
    }

    fun initViewModel(){
        //viewModel.getTodo()
        viewModel.todoLists.observe(this){
            when (it) {
                is DataState.Loading -> {
                    Timber.e("Loading")
                }
                is DataState.Success -> {
                    it.data.forEach{
                        Timber.e("Time: ${it.time} Title: ${it.title}")
                        // TODO: work this out
                    }
                }
                is DataState.Error -> {
                    Timber.e("Error")
                }
            }
        }
    }

    private fun initOnClicks() {
        binding.apply {
            settingsIcon.setOnClickListener(){

            }
            addButton.setOnClickListener(){
                findNavController().navigate(R.id.action_mainFragment_to_addTodoFragment)
            }
        }
    }
}