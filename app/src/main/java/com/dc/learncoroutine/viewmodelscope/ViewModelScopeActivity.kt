package com.dc.learncoroutine.viewmodelscope

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dc.learncoroutine.databinding.ActivityViewModelScopeBinding
import com.dc.learncoroutine.viewmodelscope.viewmodel.ViewModelScopeViewModel

class ViewModelScopeActivity : AppCompatActivity() {
    private val viewModel: ViewModelScopeViewModel by lazy {
        ViewModelProvider(this).get(ViewModelScopeViewModel::class.java)
    }
    private val binding: ActivityViewModelScopeBinding by lazy {
        ActivityViewModelScopeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
        observers()
    }

    private fun observers() {
        viewModel.response.observe(this, {
            setText(it)
        })
    }

    private fun onClickListener() {
        binding.button.setOnClickListener {
            viewModel.fetchFakeApi()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(result: String) {
        binding.message.text = binding.message.text.toString() + "\n" + result
    }
}