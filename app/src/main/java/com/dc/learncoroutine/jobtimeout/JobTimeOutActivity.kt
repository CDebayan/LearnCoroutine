package com.dc.learncoroutine.jobtimeout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dc.learncoroutine.databinding.ActivityJobTimeOutBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class JobTimeOutActivity : AppCompatActivity() {
    private val jobTimeout = 900L
    private val binding : ActivityJobTimeOutBinding by lazy {
        ActivityJobTimeOutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.button.setOnClickListener {
            /* We are creating a CoroutineScope in IO thread. For that fakeApiCall() operation is done on IO thread*/
            CoroutineScope(IO).launch {
                fakeApiCall()
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
        withContext(Dispatchers.Main){
            binding.message.text = binding.message.text.toString() + "\n" + result
        }
    }

    /**
     * We are wrapping the whole operation in withContext(IO) to make sure the operation is done IO thread.
     *
     * withTimeoutOrNull(jobTimeout) waits for the time provided. If all the operations under withTimeoutOrNull
     * scope is not finished, it returns null
     */
    private suspend fun fakeApiCall() {
        withContext(IO){
            val job = withTimeoutOrNull(jobTimeout){
                val result = getResultFromApi()
                setTextOnMainThread(result)
            }

            if(job == null){
                val cancelMessage = "Cancelling job...Job took longer than $jobTimeout ms"
                setTextOnMainThread(cancelMessage)
            }
        }
    }

    /**
     * Returns the result after 1sec
     */
    private suspend fun getResultFromApi(): String {
        delay(1000)
        return "Result"
    }
}