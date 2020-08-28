package com.dc.learncoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dc.learncoroutine.coroutinejob.CoroutineJobActivity
import com.dc.learncoroutine.databinding.ActivityMainBinding
import com.dc.learncoroutine.gettingstarted.GettingStartedActivity
import com.dc.learncoroutine.jobtimeout.JobTimeOutActivity
import com.dc.learncoroutine.lifecyclescope.LifecycleScopeActivity
import com.dc.learncoroutine.sequentialparalleltask.SequentialParallelTaskActivity
import com.dc.learncoroutine.viewmodelscope.ViewModelScopeActivity
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

        binding.sequentialParallel.setOnClickListener {
            openActivity(SequentialParallelTaskActivity::class.java)
        }

        binding.lifecycleScope.setOnClickListener {
            openActivity(LifecycleScopeActivity::class.java)
        }

        binding.viewModelScope.setOnClickListener {
            openActivity(ViewModelScopeActivity::class.java)
        }
    }
}