package com.dc.learncoroutine.livedatascope

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dc.learncoroutine.databinding.ActivityLiveDataScopeBinding
import com.dc.learncoroutine.livedatascope.viewmodel.LiveDataScopeViewModel

class LiveDataScopeActivity : AppCompatActivity() {
    private val viewModel: LiveDataScopeViewModel by lazy {
        ViewModelProvider(this).get(LiveDataScopeViewModel::class.java)
    }
    private val binding: ActivityLiveDataScopeBinding by lazy {
        ActivityLiveDataScopeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }

    private fun onClickListener() {
        binding.button.setOnClickListener {
            viewModel.fetchFakeApi().observe(this , Observer {
                setText(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(result: String) {
        binding.message.text = binding.message.text.toString() + "\n" + result
    }
}