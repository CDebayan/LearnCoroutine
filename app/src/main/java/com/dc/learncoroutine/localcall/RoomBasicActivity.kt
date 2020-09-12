package com.dc.learncoroutine.localcall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.learncoroutine.databinding.ActivityRoomBasicBinding
import com.dc.learncoroutine.localcall.adapter.TodoListAdapter
import com.dc.learncoroutine.localcall.model.TodoModel
import com.dc.learncoroutine.localcall.viewmodel.TodoViewModel
import com.dc.learncoroutine.utils.ItemClickListener
import com.dc.learncoroutine.utils.gone
import com.dc.learncoroutine.utils.show
import com.dc.learncoroutine.utils.showToast

class RoomBasicActivity : AppCompatActivity() {
    private val binding : ActivityRoomBasicBinding by lazy {
        ActivityRoomBasicBinding.inflate(layoutInflater)
    }
    private val viewModel : TodoViewModel by lazy {
        ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observers()
        onClickListener()
    }

    private fun onClickListener() {
        binding.addButton.setOnClickListener {
            viewModel.addTodo("hi","helloo").observe(this,{ state ->
                when(state){
                    is TodoViewModel.TodoState.Loading -> setLoading(true)
                    is TodoViewModel.TodoState.Error ->{
                        setLoading(false)
                        showToast(state.message)
                    }
                    is TodoViewModel.TodoState.Success ->{
                        setLoading(false)
                        showToast(state.message)
                    }
                }
            })
        }
    }

    private fun setLoading(showProgress: Boolean) {
        if(showProgress){
            binding.progress.show()
        }else{
            binding.progress.gone()
        }
    }

    private fun observers() {
        viewModel.todoList.observe(this, {
            setRecyclerView(it)
        })
    }

    private fun setRecyclerView(list: List<TodoModel>) {
        if (list.isNotEmpty()) {
            showHideViews(showRecycler = true)
            binding.recyclerView.layoutManager = LinearLayoutManager(this@RoomBasicActivity)
            binding.recyclerView.adapter = TodoListAdapter(list, object : ItemClickListener {
                override fun onItemClick(position: Int, option: String) {

                }
            })
        } else {
            binding.placeholder.text = "No Data"
            showHideViews(showPlaceHolder = true)
        }
    }

    private fun showHideViews(
        showRecycler: Boolean = false,
        showPlaceHolder: Boolean = false,
        showLoader: Boolean = false,
    ) {
        if (showRecycler) {
            binding.recyclerView.show()
        } else {
            binding.recyclerView.gone()
        }

        if (showPlaceHolder) {
            binding.placeholder.show()
        } else {
            binding.placeholder.gone()
        }

        if (showLoader) {
            binding.progress.show()
        } else {
            binding.progress.gone()
        }
    }
}