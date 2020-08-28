package com.dc.learncoroutine.singlenetworkcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.learncoroutine.databinding.ActivitySingleNetworkCallBinding
import com.dc.learncoroutine.singlenetworkcall.adapter.SingleNetworkCallAdapter
import com.dc.learncoroutine.singlenetworkcall.model.User
import com.dc.learncoroutine.utils.gone
import com.dc.learncoroutine.utils.show
import com.dc.learncoroutine.singlenetworkcall.viewmodel.SingleNetworkCallViewModel
import com.dc.learncoroutine.utils.showToast

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
        viewModel.fetchUserList().observe(this, ::handleState)
    }

    private fun handleState(state: SingleNetworkCallViewModel.State) {
        when (state) {
            is SingleNetworkCallViewModel.State.Loading -> {
                binding.progress.show()
                binding.recyclerView.gone()
            }
            is SingleNetworkCallViewModel.State.Success -> {
                binding.progress.gone()
                setRecyclerView(state.userList)
            }
            is SingleNetworkCallViewModel.State.Error -> {
                binding.progress.gone()
                binding.recyclerView.gone()
                showToast(state.message)
            }
        }
    }

    private fun setRecyclerView(userList: List<User>?) {
        userList?.let {
            binding.recyclerView.show()
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = SingleNetworkCallAdapter(it)
        } ?: also {
            binding.recyclerView.gone()
            showToast("Empty List...")
        }
    }
}