package com.kcode.ui.view.activity

import android.text.TextUtils
import com.kcode.R
import com.kcode.base.BaseActivity
import com.kcode.databinding.ActivitySignInBinding
import com.kcode.utils.DialogUtils.showInfoDialog

class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    private var mobile = ""

    override fun getViewBinding(): ActivitySignInBinding =
        ActivitySignInBinding.inflate(layoutInflater)

    override fun initSetUp() {
        onClick()
    }

    private fun onClick() {
        binding.buttonLogin.setOnClickListener {
            mobile = binding.editLoginMobile.text.toString().trim()
            if (TextUtils.isEmpty(mobile))
                showInfoDialog(
                    this,
                    msg = getString(R.string.msg_enter_mobile)
                )
            else
                lunchActivityForResult(MainActivity::class.java, isFinish = true)
        }

    }

}