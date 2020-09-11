package com.dc.learncoroutine.coroutineflow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.dc.learncoroutine.databinding.ActivityCoroutineFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CoroutineFlowActivity : AppCompatActivity() {
    private val binding: ActivityCoroutineFlowBinding by lazy {
        ActivityCoroutineFlowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }


    @SuppressLint("SetTextI18n")
    private fun onClickListener() {
        binding.basicButton.setOnClickListener {
            CoroutineScope(Main).launch {
                // is called when ever a value is received
                fetchBasicResult().collect {
                    setText(binding.basicTextView, it)
                }
            }
        }

        binding.asFlowButton.setOnClickListener {
            CoroutineScope(Main).launch {
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow().collect {
                    setText(binding.asFlowTextView, it)
                }
            }
        }

        binding.flowOfButton.setOnClickListener {
            CoroutineScope(Main).launch {
                flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect {
                    setText(binding.flowOfTextView, it)
                }
            }
        }

        binding.mapButton.setOnClickListener {
            CoroutineScope(Main).launch {
                fetchMapResult().collect {
                    setText(binding.mapTextView, it)
                }
            }
        }

        binding.filterButton.setOnClickListener {
            CoroutineScope(Main).launch {
                fetchFilterResult().collect {
                    setText(binding.filterTextView, it)
                }
            }
        }

        binding.transformButton.setOnClickListener {
            CoroutineScope(Main).launch {
                fetchTransformResult().collect {
                    if (binding.transformTextView.text.isNullOrEmpty()) {
                        binding.transformTextView.text = it.toString()
                    } else {
                        binding.transformTextView.text = binding.transformTextView.text.toString() + " , " + it
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(textView: TextView, value: Int) {
        if (textView.text.isNullOrEmpty()) {
            textView.text = value.toString()
        } else {
            textView.text = textView.text.toString() + " , " + value
        }
    }

    /**
     * Returns a value every after 1 sec
     */
    private fun fetchBasicResult(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.Default)

    private fun fetchMapResult(): Flow<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow().map {
        delay(1000)
        it * it
    }

    private fun fetchFilterResult(): Flow<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow().filter {
        delay(1000)
        it % 2 == 0
    }

    private fun fetchTransformResult(): Flow<String> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow().transform {
        delay(1000)
        emit("Emitting value $it")
    }

}