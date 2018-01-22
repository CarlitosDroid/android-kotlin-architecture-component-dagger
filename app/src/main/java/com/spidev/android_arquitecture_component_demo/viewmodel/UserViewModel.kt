package com.spidev.android_arquitecture_component_demo.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import android.util.Log

import com.spidev.android_arquitecture_component_demo.repository.UserRepository
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User
import com.spidev.android_arquitecture_component_demo.util.AbsentLiveData
import com.spidev.android_arquitecture_component_demo.vo.Resource
import java.util.*

import javax.inject.Inject

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/18/18.
 */
class UserViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val login = MutableLiveData<Int>()

    var user: LiveData<Resource<User>>

    init {
        user = Transformations.switchMap(this.login, { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                userRepository.getUser1(login)
            }
        })
    }

    fun setLogin(login: Int) {
        if (Objects.equals(this.login.value, login)) {
            return
        }
        this.login.value = login
    }
}



