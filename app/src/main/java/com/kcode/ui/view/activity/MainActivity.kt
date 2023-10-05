package com.kcode.ui.view.activity

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kcode.base.BaseActivity
import com.kcode.databinding.ActivityMainBinding
import com.kcode.ui.adapter.HomeUserListAdapter
import com.kcode.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val viewModel: HomeViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var adapter: HomeUserListAdapter


    override fun initSetUp() {

        initAdapter()
        callUserListAPI()

    }

    private fun initAdapter() {
        adapter = HomeUserListAdapter()
    }

    private fun callUserListAPI() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userList.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

}
