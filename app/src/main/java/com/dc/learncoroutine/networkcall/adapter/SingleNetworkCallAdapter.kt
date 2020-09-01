package com.dc.learncoroutine.networkcall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dc.learncoroutine.databinding.ChildSingleNetworkCallBinding
import com.dc.learncoroutine.networkcall.model.EmployeeModel

class SingleNetworkCallAdapter(private val employeesList: List<EmployeeModel>) :
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
        return employeesList.size
    }

    inner class ViewHolder(private val binding: ChildSingleNetworkCallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToViews(position: Int) {
            binding.name.text = employeesList[position].employeeName
            binding.email.text = employeesList[position].employeeSalary
            binding.phone.text = employeesList[position].employeeAge
        }
    }
}