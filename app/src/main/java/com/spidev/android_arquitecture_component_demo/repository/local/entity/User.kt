package com.spidev.android_arquitecture_component_demo.repository.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
@Entity
data class User(@PrimaryKey var id: Int, var name: String){
    constructor() : this(0, "")
}