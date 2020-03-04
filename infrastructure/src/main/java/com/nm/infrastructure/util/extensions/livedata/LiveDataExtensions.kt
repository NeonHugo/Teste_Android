package com.nm.infrastructure.util.extensions.livedata

import androidx.lifecycle.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> defaultMutableLiveData(t: T?): MutableLiveData<T> {
    val liveData = MutableLiveData<T>()
    liveData.value = t
    return liveData
}

fun <T> LiveData<T>.observeSmart(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, androidx.lifecycle.Observer { it?.also { source -> observer(source) } })
}

fun <T> LiveData<T>.reobserve(owner: LifecycleOwner, observer: (T) -> Unit) {
    removeObservers(owner)
    observeSmart(owner, observer)
}

fun <T> LiveData<Event<T>>.reObserveEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    removeObservers(owner)
    observeEvent(owner, observer)
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, EventObserver(observer))
}

fun <T, U> LiveData<T>.map(mapper: (T?) -> U) = Transformations.map(this, mapper)

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}