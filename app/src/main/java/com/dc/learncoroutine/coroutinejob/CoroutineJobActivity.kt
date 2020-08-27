package com.dc.learncoroutine.coroutinejob

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.dc.learncoroutine.databinding.ActivityCoroutineJobBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CoroutineJobActivity : AppCompatActivity() {
    private val binding: ActivityCoroutineJobBinding by lazy {
        ActivityCoroutineJobBinding.inflate(layoutInflater)
    }
    private lateinit var job1: Job
    private lateinit var job2: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        //region Example 1
        binding.startButton1.setOnClickListener {
            /* We are creating a CoroutineScope in IO thread.*/
            job1 = CoroutineScope(IO).launch {
                repeatCall()
            }
        }
        binding.cancelButton1.setOnClickListener {
            if (::job1.isInitialized) {
                job1.cancel()
                binding.message1.text = ""
            }
        }
        //endregion

        //region Example 2
        binding.startButton2.setOnClickListener {
            job2 = Job()
            /* We are creating a CoroutineScope in IO thread, and adding the job with scope*/
            CoroutineScope(IO + job2).launch {
                fakeApiCall()
            }
        }
        binding.cancelButton2.setOnClickListener {
            if (::job2.isInitialized) {
                job2.cancel()
                binding.message2.text = ""
            }
        }
        //endregion
    }

    //region Example 1

    /**
     * We are wrapping the whole operation in withContext(IO) to make sure the operation is done IO thread.
     *
     * This is used to display message every after 1sec for 10 times
     */
    private suspend fun repeatCall() {
        var count = 0
        withContext(IO) {
            repeat(10) {
                delay(1000)
                setExample1Text("Result ${count++}")
            }
        }
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     */
    @SuppressLint("SetTextI18n")
    private suspend fun setExample1Text(text: String) {
        withContext(Main) {
            if (binding.message1.text.isNullOrEmpty()) {
                binding.message1.text = text
            } else {
                binding.message1.text = binding.message1.text.toString() + " , " + text
            }
        }
    }
    //endregion

    //region Example 2

    /**
     * We are wrapping the whole operation in withContext(IO) to make sure the operation is done IO thread.
     *
     * This is used to call getResultFromApi() and display the result
     */
    private suspend fun fakeApiCall() {
        withContext(IO) {
            setExample2Text("Stated")
            val result = getResultFromApi()
            setExample2Text(result)
        }
    }

    /**
     * Returns the result after 1sec
     */
    private suspend fun getResultFromApi(): String {
        delay(1000)
        return "Result"
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     */
    @SuppressLint("SetTextI18n")
    private suspend fun setExample2Text(text: String) {
        withContext(Main) {
            if (binding.message2.text.isNullOrEmpty()) {
                binding.message2.text = text
            } else {
                binding.message2.text = binding.message2.text.toString() + " , " + text
            }
        }
    }
    //endregion

    override fun onDestroy() {
        super.onDestroy()
        // cancelling the jobs
        job1.cancel()
        job2.cancel()
    }
}