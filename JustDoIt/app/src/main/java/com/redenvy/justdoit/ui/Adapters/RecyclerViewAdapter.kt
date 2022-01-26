package com.redenvy.justdoit.ui.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redenvy.justdoit.data.model.TodoListItem
import com.redenvy.justdoit.databinding.RecyclerItemBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.TodoViewHolder>() {

    private val todos = mutableListOf<TodoListItem>()

    fun addTodos(todo: List<TodoListItem>) {
        todos.clear()
        todos.addAll(todo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.TodoViewHolder {
        val bind = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(bind)
    }

    inner class TodoViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoListItem) {
            binding.apply {
                itemTitle.text = todo.title
                itemTime.text = todo.time
            }
        }
    }

    override fun onBindViewHolder(holder : RecyclerViewAdapter.TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return todos.size
    }
}