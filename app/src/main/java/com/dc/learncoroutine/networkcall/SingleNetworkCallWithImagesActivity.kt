package com.dc.learncoroutine.networkcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.learncoroutine.databinding.ActivitySingleNetworkCallWithImagesBinding
import com.dc.learncoroutine.networkcall.adapter.SingleNetworkCallWithImagesAdapter
import com.dc.learncoroutine.networkcall.model.UserModel
import com.dc.learncoroutine.networkcall.model.UsersListModel
import com.dc.learncoroutine.networkcall.viewmodel.SingleNetworkCallWithImagesViewModel
import com.dc.learncoroutine.utils.Resource
import com.dc.learncoroutine.utils.gone
import com.dc.learncoroutine.utils.show
import com.dc.learncoroutine.utils.showToast

class SingleNetworkCallWithImagesActivity : AppCompatActivity() {
    private val binding : ActivitySingleNetworkCallWithImagesBinding by lazy {
        ActivitySingleNetworkCallWithImagesBinding.inflate(layoutInflater)
    }
    private val viewModel : SingleNetworkCallWithImagesViewModel by lazy {
        ViewModelProvider(this).get(SingleNetworkCallWithImagesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchUserList()
    }

    private fun fetchUserList() {
        viewModel.fetchUsersList().observe(this, ::handleState)
    }

    private fun handleState(state: Resource<UsersListModel>) {
        when (state) {
            is Resource.Loading -> {
                binding.progress.show()
                binding.recyclerView.gone()
            }
            is Resource.Success -> {
                binding.progress.gone()
                setRecyclerView(state.data?.userList)
            }
            is Resource.Error -> {
                binding.progress.gone()
                binding.recyclerView.gone()
                showToast(state.message)
            }
        }
    }

    private fun setRecyclerView(data: List<UserModel>?) {
        data?.let {
            binding.recyclerView.show()
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = SingleNetworkCallWithImagesAdapter(this,it)
        } ?: also {
            binding.recyclerView.gone()
            showToast("Empty List...")
        }
    }
}