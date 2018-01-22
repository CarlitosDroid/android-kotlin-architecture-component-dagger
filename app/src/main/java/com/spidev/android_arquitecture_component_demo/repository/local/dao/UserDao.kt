package com.spidev.android_arquitecture_component_demo.repository.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :userId")
    fun load(userId: Int): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}