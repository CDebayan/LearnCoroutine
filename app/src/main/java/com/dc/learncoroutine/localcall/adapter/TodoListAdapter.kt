package com.dc.learncoroutine.localcall.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dc.learncoroutine.databinding.ChildTodoListBinding
import com.dc.learncoroutine.localcall.model.TodoModel
import com.dc.learncoroutine.utils.ItemClickListener


class TodoListAdapter(private val todoList: List<TodoModel>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(
            ChildTodoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.setDataToViews(position)
        holder.setOnClickListener()
    }

    inner class TodoListViewHolder(private val binding: ChildTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setDataToViews(position: Int) {
            binding.title.text = todoList[position].title
            binding.description.text = todoList[position].description
        }

        fun setOnClickListener() {

        }
    }
}
