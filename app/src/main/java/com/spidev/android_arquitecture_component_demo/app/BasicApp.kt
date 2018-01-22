package com.spidev.android_arquitecture_component_demo.app

import android.app.Activity
import android.app.Application
import com.spidev.android_arquitecture_component_demo.BuildConfig
import com.spidev.android_arquitecture_component_demo.di.AppInjector
import com.spidev.android_arquitecture_component_demo.util.LogUtil
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
class BasicApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            LogUtil.e("", "")
        }
        AppInjector.init(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector
}