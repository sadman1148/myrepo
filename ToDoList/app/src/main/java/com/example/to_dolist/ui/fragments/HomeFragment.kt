package com.example.to_dolist.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_dolist.R
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.databinding.FragmentHomeBinding
import com.example.to_dolist.ui.adapter.RecyclerViewAdapter
import com.example.to_dolist.utils.DataState
import com.example.to_dolist.viewModel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: TodoListViewModel by viewModels()
    var sharedPreferences: SharedPreferences? = null
    private val recyclerViewAdapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initGreetings()
        init()
        return binding.root
    }

    private fun initGreetings() {
        val c: Calendar = Calendar.getInstance()
        val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
        if (timeOfDay >= 0 && timeOfDay < 5) {
            binding.timeOfDay.setText("Good Night")
        } else if (timeOfDay >= 5 && timeOfDay < 12) {
            binding.timeOfDay.setText("Good Morning")
        } else if (timeOfDay >= 12 && timeOfDay < 17) {
            binding.timeOfDay.setText("Good Afternoon")
        } else if (timeOfDay >= 17 && timeOfDay < 21) {
            binding.timeOfDay.setText("Good Evening")
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            binding.timeOfDay.setText("Good Night")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initOnClick()
    }

    private fun initOnClick() {
        sharedPreferences = this.getActivity()?.getSharedPreferences("night", 0)
        val booleanValue = sharedPreferences!!.getBoolean("night_mode", true)
        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //binding.themeChange.setChecked(true)
        }
        binding.apply {
            addTask.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addFragment)
            }
            settingsIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingsActivity)
            }
//            themeChange.setOnClickListener {
//                if (themeChange.isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                    themeChange.setChecked(true)
//                    val editor = sharedPreferences!!.edit()
//                    editor.putBoolean("night_mode", true)
//                    editor.commit()
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                    themeChange.setChecked(false)
//                    val editor = sharedPreferences!!.edit()
//                    editor.putBoolean("night_mode", false)
//                    editor.commit()
//                }
//            }
        }
    }

    private fun initRecycler() {

        binding.taskRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        viewModel.getAllTodoData.observe(viewLifecycleOwner) {
            recyclerViewAdapter.addTodos(it)
        }
    }

    private fun init() {
        viewModel.syncToLocalDB()
    }

    fun deleteTodo(id: String) {
        //viewModel.deleteTodo(id)
    }

    private fun getDataFromDB() {
        viewModel.getAllTodoData.observe(viewLifecycleOwner) {
            Timber.e(it.size.toString())

        }
    }
}