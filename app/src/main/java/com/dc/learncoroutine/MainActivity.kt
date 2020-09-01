package com.dc.learncoroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dc.learncoroutine.coroutinejob.CoroutineJobActivity
import com.dc.learncoroutine.databinding.ActivityMainBinding
import com.dc.learncoroutine.gettingstarted.GettingStartedActivity
import com.dc.learncoroutine.jobtimeout.JobTimeOutActivity
import com.dc.learncoroutine.lifecyclescope.LifecycleScopeActivity
import com.dc.learncoroutine.livedatascope.LiveDataScopeActivity
import com.dc.learncoroutine.networkcall.ParallelNetworkCallActivity
import com.dc.learncoroutine.networkcall.SequentialNetworkCallActivity
import com.dc.learncoroutine.sequentialparalleltask.SequentialParallelTaskActivity
import com.dc.learncoroutine.networkcall.SingleNetworkCallActivity
import com.dc.learncoroutine.utils.openActivity
import com.dc.learncoroutine.viewmodelscope.ViewModelScopeActivity

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

        binding.liveDataScope.setOnClickListener {
            openActivity(LiveDataScopeActivity::class.java)
        }

        binding.singleNetworkCall.setOnClickListener {
            openActivity(SingleNetworkCallActivity::class.java)
        }

        binding.parallelNetworkCall.setOnClickListener {
            openActivity(ParallelNetworkCallActivity::class.java)
        }

        binding.sequentialNetworkCall.setOnClickListener {
            openActivity(SequentialNetworkCallActivity::class.java)
        }
    }
}