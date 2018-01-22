package com.spidev.android_arquitecture_component_demo.di

import android.app.Application
import android.arch.persistence.room.Room
import com.spidev.android_arquitecture_component_demo.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "App.db").build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}