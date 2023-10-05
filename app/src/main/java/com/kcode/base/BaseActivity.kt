package com.kcode.base

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kcode.base.receiver.ConnectivityReceiver
import com.kcode.utils.gone
import com.kcode.utils.visible

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB
    abstract fun initSetUp()

    companion object {
        const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initSetUp()

        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    protected fun showKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    protected fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private var resultOKCallBack: ((Any) -> Unit)? = null
    private var resultCancelCallBack: (() -> Unit)? = null
    private var resultElseCallBack: (() -> Unit)? = null

    protected fun lunchActivityForResult(
        destinationClass: Class<*>,
        isFinish: Boolean = false,
        resultOKCallBack: ((Any) -> Unit)? = null,
        resultCancelCallBack: (() -> Unit)? = null,
        resultElseCallBack: (() -> Unit)? = null
    ) {
        this.resultOKCallBack = resultOKCallBack
        this.resultCancelCallBack = resultCancelCallBack
        this.resultElseCallBack = resultElseCallBack
        startForResult.launch(Intent(this, destinationClass))
        if (isFinish)
            finish()
    }

    var network: ((Boolean) -> Unit)? = null
    protected fun networkConnected(network: ((Boolean) -> Unit)? = null) {
        this.network = network
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        network?.invoke(isConnected)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val data = result.data
                    resultOKCallBack?.invoke(data!!)
                }
                RESULT_CANCELED -> {
                    resultCancelCallBack?.invoke()
                }
                else -> {
                    resultElseCallBack?.invoke()
                }
            }

        }

    private var progressBar: ProgressBar? = null

    fun showProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
        progressBar.visible()
    }

    fun hideProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
        progressBar.gone()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }


}

