package com.spidev.android_arquitecture_component_demo.repository.remote.api

import android.arch.lifecycle.LiveData
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User
import com.spidev.android_arquitecture_component_demo.repository.remote.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
interface WebServiceApi {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Call<UserResponse>

    @GET("users/{userId}")
    fun getUser2(@Path("userId") userId: Int): LiveData<ApiResponse<UserResponse>>
}