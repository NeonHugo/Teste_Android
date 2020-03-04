package com.nm.infrastructure.util.extensions.livecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nm.infrastructure.util.extensions.livedata.Event
import com.nm.infrastructure.util.extensions.livedata.observeEvent
import com.nm.infrastructure.util.extensions.livedata.observeSmart

fun <T : Any> LifecycleOwner.bind(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observeSmart(this) { function.invoke(it) }
}

fun <T : Any> LifecycleOwner.bindEvent(data: LiveData<Event<T>>, function: (id: T) -> Unit) {
    data.observeEvent(this) { function.invoke(it) }
}
