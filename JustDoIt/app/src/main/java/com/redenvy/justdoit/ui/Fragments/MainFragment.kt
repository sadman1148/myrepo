package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.databinding.FragmentMainBinding
import com.redenvy.justdoit.ui.Adapters.RecyclerViewAdapter
import com.redenvy.justdoit.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TodoListViewModel by viewModels()
    private val recyclerViewAdapter : RecyclerViewAdapter by lazy {
        RecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        initOnClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        viewModel.insertToLocalDbFromAPI()
        viewModel.todoLists.observe(viewLifecycleOwner){
            when (it) {
                is DataState.Loading -> {
                    Timber.e("Loading")
                }
                is DataState.Success -> {
                    it.data.forEach{
                        var tempList : MutableList<TodoListItem> = mutableListOf()
                        tempList.add(TodoListItem(
                            it.id,
                            it.title,
                            it.time,
                            it.todo
                        ))
                        recyclerViewAdapter.addTodos(tempList)
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
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
            addButton.setOnClickListener(){
                findNavController().navigate(R.id.action_mainFragment_to_addTodoFragment)
            }
        }
    }
}