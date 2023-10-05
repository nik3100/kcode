package com.kcode.ui.view.activity

import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.kcode.base.BaseActivity
import com.kcode.databinding.ActivitySplashBinding
import com.kcode.utils.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    @Inject
    lateinit var ss: String


    override fun initSetUp() {
        Handler(mainLooper).postDelayed({
            startActivity(WelcomeActivity::class.java)
        }, SPLASH_DELAY)

        Log.e(MainActivity.TAG,ss)

    }


    private fun startActivity(destinationClass: Class<*>) {
        startForResult.launch(Intent(this, destinationClass))
        finish()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val data = result.data
                }
                RESULT_CANCELED -> {

                }
                else -> {

                }
            }

        }


}
