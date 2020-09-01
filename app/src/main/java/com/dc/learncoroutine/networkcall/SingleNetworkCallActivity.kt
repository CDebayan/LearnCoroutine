package com.dc.learncoroutine.networkcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.learncoroutine.databinding.ActivitySingleNetworkCallBinding
import com.dc.learncoroutine.networkcall.adapter.SingleNetworkCallAdapter
import com.dc.learncoroutine.networkcall.model.EmployeeModel
import com.dc.learncoroutine.networkcall.model.EmployeesListModel
import com.dc.learncoroutine.networkcall.viewmodel.SingleNetworkCallViewModel
import com.dc.learncoroutine.utils.*

class SingleNetworkCallActivity : AppCompatActivity() {
    private val binding: ActivitySingleNetworkCallBinding by lazy {
        ActivitySingleNetworkCallBinding.inflate(layoutInflater)
    }
    private val viewModel: SingleNetworkCallViewModel by lazy {
        ViewModelProvider(this).get(SingleNetworkCallViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchUserList()
    }

    private fun fetchUserList() {
        viewModel.fetchEmployeesList().observe(this, ::handleState)
    }

    private fun handleState(state: Resource<EmployeesListModel>) {
        when (state) {
            is Resource.Loading -> {
                binding.progress.show()
                binding.recyclerView.gone()
            }
            is Resource.Success -> {
                binding.progress.gone()
                setRecyclerView(state.data?.employeesList)
            }
            is Resource.Error -> {
                binding.progress.gone()
                binding.recyclerView.gone()
                showToast(state.message)
            }
        }
    }

    private fun setRecyclerView(data: List<EmployeeModel>?) {
        data?.let {
            binding.recyclerView.show()
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = SingleNetworkCallAdapter(it)
        } ?: also {
            binding.recyclerView.gone()
            showToast("Empty List...")
        }
    }
}