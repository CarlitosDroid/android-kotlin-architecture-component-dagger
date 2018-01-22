package com.spidev.android_arquitecture_component_demo.ui.activities.user

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.spidev.android_arquitecture_component_demo.R
import com.spidev.android_arquitecture_component_demo.di.Injectable
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User
import com.spidev.android_arquitecture_component_demo.viewmodel.UserViewModel
import com.spidev.android_arquitecture_component_demo.vo.Resource

import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.content_user.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var userViewModel: UserViewModel
    private var userList = mutableListOf<User>()

    private lateinit var userAdapter: UserAdapter

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)

        userAdapter = UserAdapter(userList)
        rvUser.adapter = userAdapter

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        userViewModel.user.observe(this, object : Observer<Resource<User>> {
            override fun onChanged(userResource: Resource<User>?) {
                if(userResource!!.data != null){
                    userAdapter.addUser(userResource.data!!)
                }
            }
        })

        fab.setOnClickListener { view ->
            userViewModel.setLogin(1)
        }
    }
}
