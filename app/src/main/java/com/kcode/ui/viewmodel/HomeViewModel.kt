package com.kcode.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kcode.data.datasource.HomeDataSource
import com.kcode.data.model.Data
import com.kcode.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val networkHelper: NetworkHelper
) : AndroidViewModel(application) {

    val userList: Flow<PagingData<Data>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { HomeDataSource(networkHelper) }
        ).flow.cachedIn(viewModelScope)


}