package com.redenvy.justdoit.ui.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.FragmentMainBinding
import com.redenvy.justdoit.ui.Adapters.TodoRecyclerViewAdapter
import com.redenvy.justdoit.utils.hide
import com.redenvy.justdoit.utils.show
import dagger.hilt.android.AndroidEntryPoint
import android.net.NetworkInfo

import android.net.ConnectivityManager
import com.redenvy.justdoit.utils.BroadCaster
import com.redenvy.justdoit.utils.CustomFunctions

@AndroidEntryPoint
class MainFragment : Fragment() {
    var alertDialog: AlertDialog? = null
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TodoListViewModel by viewModels()
    private val todoRecyclerViewAdapter: TodoRecyclerViewAdapter by lazy {
        TodoRecyclerViewAdapter()
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
        initOnClicks()
        initRecycler()
        initObserver()
    }

    private fun initObserver() {
        viewModel.localTodoList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                BroadCaster.sendNewBroadcast(requireContext())
            }
        }
    }

    private fun initRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoRecyclerViewAdapter
        }
        viewModel.localTodoList.observe(viewLifecycleOwner) {
            todoRecyclerViewAdapter.addTodos(it)
            if (todoRecyclerViewAdapter.itemCount <= 0) {
                binding.noTodoImage.show()
                binding.noTodoText.show()
            } else {
                binding.noTodoImage.hide()
                binding.noTodoText.hide()
            }
        }
    }

    /**
     * Checks if Wifi or Data connection is ON or not. However, it does not check if the internet is accessible
     */
    fun isConnectingToInternet(): Boolean {
        val connectivity =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
            return true
        }
        return false
    }

    private fun initOnClicks() {
        binding.apply {
            settingsIcon.setOnClickListener() {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
            addButton.setOnClickListener() {
                findNavController().navigate(R.id.action_mainFragment_to_addTodoFragment)
            }
            syncIcon.setOnClickListener() {
                val alertDialogBuilder = AlertDialog.Builder(context)
                alertDialogBuilder.setTitle(getString(R.string.sync_with_online))
                alertDialogBuilder.setMessage(getString(R.string.sync_now))
                alertDialogBuilder.setPositiveButton(getString(R.string.yes)) { _: DialogInterface, _: Int ->
                    if (isConnectingToInternet()){
                        viewModel.syncToLocalDbFromAPI()
                    }
                    else{
                        Toast.makeText(context, getString(R.string.connect_to_internet), Toast.LENGTH_SHORT).show()
                    }
                }
                alertDialogBuilder.setNegativeButton(
                    getString(R.string.no),
                    { dialogInterface: DialogInterface, i: Int -> })
                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
            }
        }
    }
}