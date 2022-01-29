package com.example.to_dolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.R
import com.example.to_dolist.data.localDatabase.TodoListItem
import com.example.to_dolist.databinding.RecyclerviewRowBinding
import com.example.to_dolist.utils.Constants
import com.google.gson.Gson
import java.text.SimpleDateFormat

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.TodoViewHolder>() {
    private val todos = mutableListOf<TodoListItem>()
    fun addTodos(todo: List<TodoListItem>) {
        todos.clear()
        todos.addAll(todo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.TodoViewHolder {
        val bind =
            RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(bind)
    }

    inner class TodoViewHolder(private val binding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoListItem) {
            binding.apply {
                tvTitle.text = todo.title
                val datetime = SimpleDateFormat("EE dd MMM yyyy hh:mm a").format(todo.time)
                tvTime.text = datetime
                recyclerItem.setOnClickListener {
                    val data = bundleOf(Constants.PACKAGE_NAME to Gson().toJson(todo))
                    it.findNavController()
                        .navigate(R.id.action_homeFragment_to_detailsFragment, data)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return todos.size
    }
}