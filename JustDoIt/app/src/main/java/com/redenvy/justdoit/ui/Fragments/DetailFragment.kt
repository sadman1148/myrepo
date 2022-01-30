package com.redenvy.justdoit.ui.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redenvy.justdoit.R
import com.redenvy.justdoit.ViewModel.TodoListViewModel
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.databinding.FragmentDetailBinding
import com.redenvy.justdoit.utils.Constants
import com.redenvy.justdoit.utils.CustomFunctions
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class DetailFragment : Fragment() {
    var alertDialog: AlertDialog? = null
    private lateinit var todo: TodoListItem
    private lateinit var receivedTodo: String
    private lateinit var tempTodo: TodoListItem
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: TodoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initClicks()
    }

    private fun initObserver(){
        viewModel.isGetAllowed.observe(viewLifecycleOwner){
            if(it){
                todo = viewModel.tempTodoListItem
                binding.detailTitle.setText(todo.title)
                val datetime = SimpleDateFormat(Constants.FORMAT_TO_STRING_PATTERN).format(todo.time)
                val timeList : List<String> = datetime.split(" ")

                binding.detailDate.setText(timeList[1]+" "+timeList[2]+"\n"+timeList[3])
                binding.detailTime.setText(timeList[4]+" "+timeList[5]+"\n"+timeList[0])

                var subTodo : String = ""
                for (thing in todo.todo){
                    subTodo += "• "+thing+"\n"
                }
                binding.detailSubTodo.setText(subTodo)
            }
        }
    }

    private fun initView() {
        arguments?.getString(Constants.BUNDLE_NAME)?.let { receivedTodo = it }
        tempTodo = Gson().fromJson(receivedTodo, CustomFunctions.getTodoType())
        viewModel.getTodoById(tempTodo.id)
    }

    private fun initClicks() {
        binding.apply {
            detailDeleteButton.setOnClickListener() {
                val alertDialogBuilder = AlertDialog.Builder(context)
                alertDialogBuilder.setTitle("Delete Todo")
                alertDialogBuilder.setMessage("Are you sure you want to delete?")
                alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    viewModel.deleteTodo(todo)
                    findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
                }
                alertDialogBuilder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
            }
            detailEditButton.setOnClickListener(){
                findNavController().navigate(R.id.action_detailFragment_to_updateTodoFragment,
                    bundleOf(Constants.BUNDLE_NAME to Gson().toJson(todo)))
            }
        }
    }
}