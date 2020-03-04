package com.nm.infrastructure.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

abstract class DefaultViewModel : ViewModel(), LifecycleObserver, KoinComponent