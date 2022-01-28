package com.redenvy.justdoit.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.FragmentMainBinding
import com.redenvy.justdoit.ui.Adapters.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sync()
        initOnClicks()
        initRecycler()
    }

    private fun sync(){
        viewModel.syncToLocalDbFromAPI()
    }

    private fun initRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        viewModel.localTodoList.observe(viewLifecycleOwner){
            recyclerViewAdapter.addTodos(it)
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