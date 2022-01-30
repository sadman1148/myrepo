package com.example.to_dolist.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_dolist.R
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.databinding.FragmentDetailsBinding
import com.example.to_dolist.utils.Constants
import com.example.to_dolist.viewModel.TodoListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var data: String
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var todo: TodoListItem
    private val viewModel: TodoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTodoInDetails()
        initOnClick()
    }

    private fun showTodoInDetails() {
        arguments?.getString(Constants.PACKAGE_NAME)?.let { data = it }
        val type = object : TypeToken<TodoListItem>() {}.type
        todo = Gson().fromJson(data, type)
        binding.showTaskTitle.setText(todo.title)
        val datetime = SimpleDateFormat("EE dd MMM yyyy hh:mm aa").format(todo.time)
        binding.showTaskDateTime.setText(datetime)
        binding.showSubTask.setText(todo.todo.joinToString("\n"))
    }

    private fun initOnClick() {
        binding.apply {
            moreVertical.setOnClickListener {
                val menuItemView =
                    view!!.findViewById<View>(com.example.to_dolist.R.id.moreVertical)
                val popupMenu = PopupMenu(activity!!, menuItemView)
                popupMenu.menuInflater.inflate(com.example.to_dolist.R.menu.popup_menu,
                    popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.deleteBtn -> {
                            AlertDialog.Builder(activity!!)
                                .setTitle("Confirm delete")
                                .setIcon(R.drawable.ic_warning)
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton("Yes") { dialog, which ->
                                    viewModel.deleteTodoList(todo)
                                    Toast.makeText(activity,
                                        "Successfully deleted",
                                        Toast.LENGTH_SHORT).show();
                                    findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
                                }
                                .setNegativeButton("No", null).show()
                        }
                        R.id.editBtn -> {
                            val dataFromDetailsToUpdate =
                                bundleOf(Constants.DATA_FROM_DETAILS_TO_UPDATE to Gson().toJson(todo))
                            findNavController().navigate(R.id.action_detailsFragment_to_updateFragment,
                                dataFromDetailsToUpdate)
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }
}