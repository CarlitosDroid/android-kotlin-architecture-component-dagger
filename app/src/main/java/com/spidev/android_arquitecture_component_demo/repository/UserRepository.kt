package com.spidev.android_arquitecture_component_demo.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User
import com.spidev.android_arquitecture_component_demo.repository.local.dao.UserDao
import com.spidev.android_arquitecture_component_demo.repository.remote.api.ApiResponse
import com.spidev.android_arquitecture_component_demo.repository.remote.api.WebServiceApi
import com.spidev.android_arquitecture_component_demo.repository.remote.model.UserResponse
import com.spidev.android_arquitecture_component_demo.vo.AppExecutors
import com.spidev.android_arquitecture_component_demo.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
@Singleton
class UserRepository @Inject constructor(val appExecutors: AppExecutors, val userDao: UserDao, val webServiceApi: WebServiceApi) {

    fun getUser1(userId: Int): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, UserResponse>(appExecutors) {
            override fun saveCallResult(userResponse: UserResponse) {
                val user = User(userResponse.id, userResponse.name!!)
                Log.e("saveCallResult", " saveCallResult " + user.id)
                Log.e("saveCallResult", " saveCallResult " + user.name)
                userDao.insert(user)
            }

            override fun shouldFetch(data: User?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<User> {
                return userDao.load(userId)
            }

            override fun createCall(): LiveData<ApiResponse<UserResponse>> {
                return webServiceApi.getUser2(userId)
            }

        }.asLiveData()
    }
}

