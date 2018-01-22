package com.spidev.android_arquitecture_component_demo.repository.local

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.InvalidationTracker
import android.arch.persistence.room.RoomDatabase
import com.spidev.android_arquitecture_component_demo.repository.local.dao.UserDao
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    override fun createInvalidationTracker(): InvalidationTracker? = null

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper? = null
}