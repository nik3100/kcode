package com.kcode.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.kcode.data.repository.MainRepository
import com.kcode.netwrok.api.ApiResult
import com.kcode.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    application: Application,
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : AndroidViewModel(application) {

    val apiData = MediatorLiveData<Any>()

    fun getUserList() {
        viewModelScope.launch {
            when {
                !networkHelper.isNetworkConnected() -> {
                    apiData.value = ApiResult.NoInternet
                }
                else -> {
                    apiData.addSource(mainRepository.getUserList()) {
                        apiData.value = it
                    }
                }
            }

        }
    }
}