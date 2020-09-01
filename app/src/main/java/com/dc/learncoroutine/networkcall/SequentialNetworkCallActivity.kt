package com.dc.learncoroutine.networkcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dc.learncoroutine.databinding.ActivitySequentialNetworkCallBinding
import com.dc.learncoroutine.networkcall.model.EmployeeModel
import com.dc.learncoroutine.networkcall.viewmodel.SequentialNetworkCallViewModel
import com.dc.learncoroutine.utils.gone
import com.dc.learncoroutine.utils.show
import com.dc.learncoroutine.utils.showToast

class SequentialNetworkCallActivity : AppCompatActivity() {
    private val binding: ActivitySequentialNetworkCallBinding by lazy {
        ActivitySequentialNetworkCallBinding.inflate(layoutInflater)
    }
    private val viewModel: SequentialNetworkCallViewModel by lazy {
        ViewModelProvider(this).get(SequentialNetworkCallViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchUsers()
    }

    private fun fetchUsers() {
        viewModel.fetchUser().observe(this, ::handleState)
    }

    private fun handleState(state: SequentialNetworkCallViewModel.Resource) {
        when (state) {
            is SequentialNetworkCallViewModel.Resource.Loading -> {
                binding.progress.show()
                binding.mainLayout.gone()
            }
            is SequentialNetworkCallViewModel.Resource.Success -> {
                binding.progress.gone()
                binding.mainLayout.show()
                setDataToViews(state.data, state.data1)
            }
            is SequentialNetworkCallViewModel.Resource.Error -> {
                binding.progress.gone()
                binding.mainLayout.gone()
                showToast(state.message)
            }
        }
    }

    private fun setDataToViews(data: EmployeeModel?, data1: EmployeeModel?) {
        binding.name.text = data?.employeeName
        binding.salary.text = data?.employeeSalary
        binding.age.text = data?.employeeAge

        binding.name1.text = data1?.employeeName
        binding.salary1.text = data1?.employeeSalary
        binding.age1.text = data1?.employeeAge
    }
}