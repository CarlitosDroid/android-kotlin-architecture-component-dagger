package com.spidev.android_arquitecture_component_demo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
