package com.kcode.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kcode.data.model.Data
import com.kcode.netwrok.api.ApiUserInterface
import com.kcode.utils.NetworkHelper
import javax.inject.Inject

class HomeDataSource(networkHelper: NetworkHelper) : PagingSource<Int, Data>() {

    @Inject
    lateinit var apiService: ApiUserInterface

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = apiService.getUserList(nextPageNumber)

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.total!!) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}