package com.dc.learncoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dc.learncoroutine.coroutinejob.CoroutineJobActivity
import com.dc.learncoroutine.databinding.ActivityMainBinding
import com.dc.learncoroutine.gettingstarted.GettingStartedActivity
import com.dc.learncoroutine.jobtimeout.JobTimeOutActivity
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onClickListener()
    }

    private fun onClickListener() {
        binding.gettingStated.setOnClickListener {
            openActivity(GettingStartedActivity::class.java)
        }

        binding.jobTimeOut.setOnClickListener {
            openActivity(JobTimeOutActivity::class.java)
        }

        binding.coroutineJob.setOnClickListener {
            openActivity(CoroutineJobActivity::class.java)
        }
    }
}