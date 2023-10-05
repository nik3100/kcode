package com.kcode.netwrok.api

import com.kcode.data.model.HomeUserResponse
import com.kcode.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos")
    fun getServices(): Call<List<ResponseModel>>

}

interface ApiUserInterface {

    @GET("data/v1/user")
     fun getUserList(@Query("page") page: Int):HomeUserResponse

}