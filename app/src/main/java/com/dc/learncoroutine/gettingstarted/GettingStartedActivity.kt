package com.dc.learncoroutine.gettingstarted

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dc.learncoroutine.databinding.ActivityGettingStartedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GettingStartedActivity : AppCompatActivity() {
    private val binding: ActivityGettingStartedBinding by lazy {
        ActivityGettingStartedBinding.inflate(layoutInflater)
    }
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }

    private fun onClickListener() {
        binding.button.setOnClickListener {
            /* We are creating a CoroutineScope in IO thread. For that fakeApiCall() operation is done on IO thread*/
            CoroutineScope(IO).launch {
                val result = fakeApiCall()
                setTextOnMainThread(result)
            }
        }
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     *
     * withContext can only be called only from a coroutine or another suspend function
     */
    @SuppressLint("SetTextI18n")
    private suspend fun setTextOnMainThread(result: String) {
        withContext(Main){
            binding.message.text = binding.message.text.toString() + "\n" + result
        }
    }

    private suspend fun fakeApiCall(): String {
        delay(1000)
        return "Result ${count++}"
    }
}