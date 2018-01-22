package com.spidev.android_arquitecture_component_demo.vo

import com.spidev.android_arquitecture_component_demo.vo.Status.ERROR
import com.spidev.android_arquitecture_component_demo.vo.Status.LOADING
import com.spidev.android_arquitecture_component_demo.vo.Status.SUCCESS

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
