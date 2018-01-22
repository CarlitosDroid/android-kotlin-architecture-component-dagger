package com.spidev.android_arquitecture_component_demo.di

import android.app.Application
import com.spidev.android_arquitecture_component_demo.app.BasicApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, MainActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(basicApp: BasicApp)
}