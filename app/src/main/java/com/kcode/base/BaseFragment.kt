package com.kcode.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kcode.utils.gone
import com.kcode.utils.visible

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract fun getViewBinding(): VB
    abstract fun initSetUp()

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetUp()
    }


    private var resultOKCallBack: ((Any) -> Unit)? = null
    private var resultCancelCallBack: (() -> Unit)? = null
    private var resultElseCallBack: (() -> Unit)? = null

    protected fun lunchActivityForResult(
        context: Context,
        destinationClass: Class<*>,
        resultOKCallBack: ((Any) -> Unit)? = null,
        resultCancelCallBack: (() -> Unit)? = null,
        resultElseCallBack: (() -> Unit)? = null
    ) {
        this.resultOKCallBack = resultOKCallBack
        this.resultCancelCallBack = resultCancelCallBack
        this.resultElseCallBack = resultElseCallBack
        startForResult.launch(Intent(context, destinationClass))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            when (result.resultCode) {
                AppCompatActivity.RESULT_OK -> {
                    val data = result.data
                    resultOKCallBack?.invoke(data!!)
                }
                AppCompatActivity.RESULT_CANCELED -> {
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
}