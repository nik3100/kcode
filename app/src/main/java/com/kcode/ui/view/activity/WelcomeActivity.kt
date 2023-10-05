package com.kcode.ui.view.activity


import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.kcode.R
import com.kcode.base.BaseActivity
import com.kcode.base.ObserveInActivity
import com.kcode.data.model.ResponseModel
import com.kcode.databinding.ActivityWelcomeBinding
import com.kcode.netwrok.api.ApiResult
import com.kcode.ui.adapter.WelcomeViewPagerAdapter
import com.kcode.ui.viewmodel.WelcomeViewModel
import com.kcode.utils.DialogUtils.showInfoDialog
import com.kcode.utils.DialogUtils.showNoInternetDialog
import com.kcode.utils.LogUtils.showELog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    private lateinit var adapter: WelcomeViewPagerAdapter
    private var welcomeList = ArrayList<ResponseModel>()
    private var selectedPosition = -1
    private var firstIndex = 0
    private val viewModel: WelcomeViewModel by viewModels()

    companion object {
        const val TAG = "WelcomeActivity"
    }

    override fun getViewBinding(): ActivityWelcomeBinding =
        ActivityWelcomeBinding.inflate(layoutInflater)

    override fun initSetUp() {

        setUpViewPager()
        setListener()
        observeData()
        apiCall()

    }

    private fun apiCall() {
        viewModel.getUserList()
    }

    private fun observeData() {
        viewModel.apiData.observe(
            this, observeInActivity
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.apiData.removeObserver(observeInActivity)
    }

    private fun hideShowProgressBar(d: ApiResult.IsLoading) {
        if (d.isLoading) {
            showProgressBar(binding.progressbarWelcome)
        } else {
            hideProgressBar(binding.progressbarWelcome)
        }
    }

    private fun setListener() {
        binding.txtViewpagerStep.setOnClickListener {
            if (welcomeList.size > 0) {
                val lastIndex = welcomeList.size - 1
                when (selectedPosition) {
                    firstIndex -> {
                        lunchActivityForResult(
                            SignInActivity::class.java,
                            isFinish = true,
                            resultOKCallBack = {
                                val data = (it as Intent).data
                                showELog(TAG, "result ok")
                            },
                            resultCancelCallBack = {
                                showELog(TAG, "result cancelled")
                            })
                    }
                    lastIndex -> {
                        lunchActivityForResult(SignInActivity::class.java, true)
                    }
                    else -> {
                        binding.viewpager2Welcome.currentItem = selectedPosition + 1
                    }
                }
            }
        }

        binding.viewpager2Welcome.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                selectedPosition = position
                if (position == firstIndex) {
                    binding.txtViewpagerStep.text = getString(R.string.lbl_skip)
                } else {
                    binding.txtViewpagerStep.text = getString(R.string.lbl_next)
                }
            }
        })

        networkConnected {
            showELog(TAG, "networkConnected $it")
        }
    }

    private fun setUpViewPager() {

        welcomeList.add(ResponseModel())
        welcomeList.add(ResponseModel())
        welcomeList.add(ResponseModel())

        adapter = WelcomeViewPagerAdapter(welcomeList)
        binding.viewpager2Welcome.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewpager2Welcome)
    }

    private val observeInActivity = ObserveInActivity.observer(
        this,
        onSuccess = {
            /*  // submit data to adapetr
                 val data = it.data as List<ResponseModel>
                 welcomeList.addAll(data)
                 adapter.submitData(welcomeList)*/
        },
        onFailure = {
            showInfoDialog(this)
        },
        onIsLoading = {
            val d = it as ApiResult.IsLoading
            hideShowProgressBar(d)
        },
    )

    private val apiResponse = Observer<Any> {
        when (it) {
            is ApiResult.Success<*> -> {
                /*  // submit data to adapetr
                  val data = it.data as List<ResponseModel>
                  welcomeList.addAll(data)
                  adapter.submitData(welcomeList)*/
            }
            is ApiResult.Failure -> {
                showInfoDialog(this, msg = it.message)
            }
            is ApiResult.Error -> {
                showInfoDialog(this)
            }
            is ApiResult.ErrorWithCode -> {
                showInfoDialog(this, msg = it.message)
            }
            is ApiResult.NoInternet -> {
                showNoInternetDialog(this)
            }
            is ApiResult.Exception -> {
                showInfoDialog(this, msg = it.message)
            }
            is ApiResult.IsLoading -> {
                if (it.isLoading) {
                    showProgressBar(binding.progressbarWelcome)
                } else {
                    hideProgressBar(binding.progressbarWelcome)
                }
            }
        }

    }


}


