package com.dc.learncoroutine.singlenetworkcall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dc.learncoroutine.databinding.ChildSingleNetworkCallBinding
import com.dc.learncoroutine.singlenetworkcall.model.User
import org.jetbrains.annotations.NotNull

class SingleNetworkCallAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<SingleNetworkCallAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildSingleNetworkCallBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataToViews(position)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(private val binding: ChildSingleNetworkCallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToViews(position: Int) {
            binding.name.text = userList[position].name
            binding.username.text = userList[position].username
            binding.email.text = userList[position].email
            binding.phone.text = userList[position].phone
        }
    }
}