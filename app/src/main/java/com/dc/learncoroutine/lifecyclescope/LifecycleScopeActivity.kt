package com.dc.learncoroutine.lifecyclescope

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dc.learncoroutine.databinding.ActivityLifecycleScopeBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class LifecycleScopeActivity : AppCompatActivity() {
    private val binding : ActivityLifecycleScopeBinding by lazy {
        ActivityLifecycleScopeBinding.inflate(layoutInflater)
    }
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }

    private fun onClickListener() {
        binding.button.setOnClickListener {
            /* We are creating a lifecycleScope in IO thread. For that fakeApiCall() operation is done on IO thread.
            * lifecycleScope automatically cancels the job when activity is destroyed
            */
            lifecycleScope.launch(IO) {
                Log.d("coroutineTest",Thread.currentThread().name)
                val result = fakeApiCall()
                setTextOnMainThread(result)
            }
        }
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     */
    @SuppressLint("SetTextI18n")
    private suspend fun setTextOnMainThread(result: String) {
        withContext(Dispatchers.Main){
            binding.message.text = binding.message.text.toString() + "\n" + result
        }
    }

    private suspend fun fakeApiCall(): String {
        delay(3000)
        Log.d("coroutineTest",Thread.currentThread().name + "api")
        return "Result ${count++}"
    }
}