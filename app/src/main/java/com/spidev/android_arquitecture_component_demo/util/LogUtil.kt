package com.spidev.android_arquitecture_component_demo.util

import android.util.Log
import java.io.IOException

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
object LogUtil {
    fun e(ioException: IOException, message: String) {
        Log.e(ioException.message, message)
    }

    fun e(string: String, message: String) {
        Log.e(string, message)
    }
}