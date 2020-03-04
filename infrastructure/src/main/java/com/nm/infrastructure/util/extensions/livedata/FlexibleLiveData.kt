package com.nm.infrastructure.util.extensions.livedata

import androidx.lifecycle.MutableLiveData

class FlexibleLiveData<T> : MutableLiveData<T>() {

    companion object {
        fun <T> default(t: T?): FlexibleLiveData<T> {
            val liveData =
                FlexibleLiveData<T>()
            liveData.value = t
            return liveData
        }
    }
}