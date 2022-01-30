package com.redenvy.justdoit.ui.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.databinding.FragmentMainBinding
import com.redenvy.justdoit.ui.Adapters.RecyclerViewAdapter
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.hide
import com.redenvy.justdoit.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    var alertDialog: AlertDialog? = null
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TodoListViewModel by viewModels()
    private val recyclerViewAdapter: RecyclerViewAdapter by lazy {
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
        initOnClicks()
        initRecycler()
        initObserver()
    }

    private fun initObserver() {
        viewModel.localTodoList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val intent = Intent(Constants.INTENT_ACTION).putExtra(
                    Constants.BUNDLE_NAME,
                    Gson().toJson(it.first())
                )
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
                requireContext().sendBroadcast(Intent(Constants.INTENT_ACTION)
                    .putExtra(Constants.BUNDLE_NAME, Gson().toJson(it.first()))
                )
            }
        }
    }


    private fun initRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        viewModel.localTodoList.observe(viewLifecycleOwner) {
            recyclerViewAdapter.addTodos(it)
            if (recyclerViewAdapter.itemCount <= 0) {
                binding.noTodoImage.show()
                binding.noTodoText.show()
            } else {
                binding.noTodoImage.hide()
                binding.noTodoText.hide()
            }
        }
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
                alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    viewModel.syncToLocalDbFromAPI()
                    //TODO: Do this in a coroutine on complete
                    Toast.makeText(context, getString(R.string.sync_complete), Toast.LENGTH_SHORT)
                        .show()
                }
                alertDialogBuilder.setNegativeButton(
                    "No",
                    { dialogInterface: DialogInterface, i: Int -> })
                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
            }
        }
    }
}