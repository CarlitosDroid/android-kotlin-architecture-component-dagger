package com.spidev.android_arquitecture_component_demo.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spidev.android_arquitecture_component_demo.viewmodel.GithubViewModelFactory
import com.spidev.android_arquitecture_component_demo.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}