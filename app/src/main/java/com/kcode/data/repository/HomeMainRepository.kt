package com.kcode.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeMainRepository @Inject constructor(@ApplicationContext context: Context) {

    /*
    companion object {
      private val apiService by lazy {
            RetrofitClient.apiInterface
        }

      fun getUserList(): MutableLiveData<Any> {
          val apiData = MutableLiveData<Any>()
          try {
              apiData.value = ApiResult.IsLoading(true)
              apiService.getServices().enqueue(object : Callback<List<ResponseModel>> {
                  override fun onResponse(
                      call: Call<List<ResponseModel>>,
                      response: Response<List<ResponseModel>>
                  ) {
                      apiData.value = ApiResult.IsLoading(false)
                      if (response.isSuccessful) {
                          val data = response.body() as List<ResponseModel>
                          apiData.value = ApiResult.Success(data)
                      } else {
                          apiData.value =
                              ApiResult.ErrorWithCode(response.code(), response.message())
                      }
                  }

                  override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                      apiData.value = ApiResult.IsLoading(false)
                      apiData.value = ApiResult.Failure(t.message.nullSafe())
                  }
              })
          } catch (e: java.lang.Exception) {
              apiData.value = ApiResult.Exception(e.message.toString())
          }
          return apiData
      }


  }
       */

}