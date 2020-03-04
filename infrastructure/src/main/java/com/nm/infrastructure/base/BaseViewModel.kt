package com.nm.infrastructure.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.util.error.ResourcesStringError
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData
import kotlinx.coroutines.*
import org.koin.core.inject

abstract class BaseViewModel(private val network: Network) : DefaultViewModel() {

    val loading: LiveData<Boolean> get() = _loading

    val empty: LiveData<Boolean> get() = _empty

    val error: LiveData<Boolean> get() = _error
    
    protected val resourcesStringError: ResourcesStringError by inject()

    protected val _loading =
        FlexibleLiveData<Boolean>()
    protected val _empty =
        FlexibleLiveData<Boolean>()

    protected val _error =
        FlexibleLiveData<Boolean>()

    protected val _noConnectionError =
        FlexibleLiveData<String>()

    protected fun launchDataLoad(
        checkConnection: Boolean = false,
        block: suspend () -> Unit
    ): Job {
        return  viewModelScope.launch {
            if ((checkConnection && network.hasActiveInternetConnection()) || !checkConnection) {
                try {
                    _loading.value = true
                    block()
                } catch (error: Exception) {
                    doOnError(error)
                } finally {
                    _loading.value = false
                }
            }
            else _noConnectionError.value = resourcesStringError.noConnectionError
        }
    }

    open fun doOnError(throwable: Throwable) {
    }

    companion object {
        const val EMPTY_TEXT = ""
        const val DEFAULT_DELAY = 750L
    }
}