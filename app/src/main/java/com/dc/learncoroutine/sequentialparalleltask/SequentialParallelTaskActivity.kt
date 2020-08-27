package com.dc.learncoroutine.sequentialparalleltask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dc.learncoroutine.databinding.ActivitySequentialParallelTaskBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

class SequentialParallelTaskActivity : AppCompatActivity() {
    private val binding: ActivitySequentialParallelTaskBinding by lazy {
        ActivitySequentialParallelTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }

    private fun onClickListener() {
        binding.sequentialButton.setOnClickListener {
            CoroutineScope(IO).launch {
                fetchApiSequential()
            }
        }

        binding.parallelButton.setOnClickListener {
            CoroutineScope(IO).launch {
                fetchApiParallel()
            }
        }
    }

    /**
     * Here each statement executed one after another sequentially
     *
     * As the two apis are called sequentially, total execution takes 2sec
     */
    private suspend fun fetchApiSequential() {
        withContext(IO) {
            val timeElapsed = measureTimeMillis {
                val res1 = getResultFromApi("Result 1")
                val res2 = getResultFromApi("Result 2")
                val finalRes = res1 + res2
                setMessage(finalRes)
            }
            setTime(timeElapsed)
        }
    }

    /**
     * Here the two apis are called in parallel, await() is used to wait until the result is fetched from api.
     *
     * As the two apis are called in parallel, total execution takes 1sec
      */
    private suspend fun fetchApiParallel() {
        withContext(IO) {
            val timeElapsed = measureTimeMillis {
                val res1 = async { getResultFromApi("Result 1") }
                val res2 = async { getResultFromApi("Result 2") }
                val finalRes = res1.await() + res2.await()
                setMessage(finalRes)
            }
            setTime(timeElapsed)
        }
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     */
    @SuppressLint("SetTextI18n")
    private suspend fun setTime(timeElapsed: Long) {
        withContext(Main) {
            binding.time.text = "$timeElapsed milliSec"
        }
    }

    /**
     * When ever we are updating any value we must do the operation in main thread. So we switched the current
     * context from IO to Main.
     */
    private suspend fun setMessage(finalRes: String) {
        withContext(Main) {
            binding.message.text = finalRes
        }
    }

    /**
     * Returns the result after 1sec
     */
    private suspend fun getResultFromApi(text: String): String {
        delay(1000)
        return text
    }

}