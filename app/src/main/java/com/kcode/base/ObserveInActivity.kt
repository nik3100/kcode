package com.kcode.base

import android.app.Activity
import androidx.lifecycle.Observer
import com.kcode.netwrok.api.ApiResult
import com.kcode.utils.DialogUtils

object ObserveInActivity {
    fun observer(
        activity: Activity,
        onSuccess: ((Any) -> Unit)? = null,
        onFailure: ((Any) -> Unit)? = null,
        onErrorWithCode: ((Any) -> Unit)? = null,
        onException: ((Any) -> Unit)? = null,
        onValidation: ((Any) -> Unit)? = null,
        onIsLoading: ((Any) -> Unit)? = null,
        onNoInternet: (() -> Unit)? = null,
        onError: ((Any) -> Unit)? = null
    ): Observer<Any> = Observer<Any> {
        when (it) {
            is ApiResult.Success<*> -> {
                onSuccess?.invoke(it)
            }
            is ApiResult.Failure -> {
                onFailure?.invoke(it)
            }
            is ApiResult.Error -> {
                DialogUtils.showInfoDialog(activity)
            }
            is ApiResult.ErrorWithCode -> {
                onErrorWithCode?.invoke(it)
            }
            is ApiResult.NoInternet -> {
                DialogUtils.showNoInternetDialog(activity)
            }
            is ApiResult.Exception -> {
                DialogUtils.showInfoDialog(activity)
            }
            is ApiResult.IsLoading -> {
                onIsLoading?.invoke(it)
            }
            is ApiResult.Validation -> {
                onValidation?.invoke(it)
            }
        }
    }
}
