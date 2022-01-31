package com.redenvy.justdoit.ui.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.redenvy.justdoit.R
import com.redenvy.justdoit.data.local.TodoListItem
import com.redenvy.justdoit.databinding.RecyclerItemBinding
import com.redenvy.justdoit.utils.Constants
import java.text.SimpleDateFormat

class TodoRecyclerViewAdapter : RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>() {

    private val todos = mutableListOf<TodoListItem>()

    fun addTodos(todo: List<TodoListItem>) {
        todos.clear()
        todos.addAll(todo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoRecyclerViewAdapter.TodoViewHolder {
        val bind = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(bind)
    }

    inner class TodoViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoListItem) {
            binding.apply {
                itemTitle.setText(todo.title)
                val datetime =
                    SimpleDateFormat(Constants.FORMAT_TO_STRING_PATTERN).format(todo.time)
                val timeList: List<String> = datetime.split(" ")
                itemTime.setText(timeList[4] + " " + timeList[5])
                itemDate.setText(timeList[0] + " " + timeList[1] + " " + timeList[2] + " " + timeList[3])
                touchable.setOnClickListener() {
                    it.findNavController().navigate(
                        R.id.action_mainFragment_to_detailFragment,
                        bundleOf(Constants.BUNDLE_NAME to Gson().toJson(todo))
                    )
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TodoRecyclerViewAdapter.TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int = todos.size
}