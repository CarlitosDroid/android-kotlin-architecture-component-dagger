package com.spidev.android_arquitecture_component_demo.di

import com.spidev.android_arquitecture_component_demo.ui.activities.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun contributeMainActivity(): UserActivity
}