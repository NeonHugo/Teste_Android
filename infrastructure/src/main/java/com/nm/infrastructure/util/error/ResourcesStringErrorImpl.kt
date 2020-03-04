package com.nm.infrastructure.util.error

import android.content.Context
import com.nm.infrastructure.R

class ResourcesStringErrorImpl(context: Context) : ResourcesStringError {

    private val context = context.applicationContext

    override val genericError: String get() = context.getString(R.string.generic_error)
    override val noConnectionError: String get() = context.getString(R.string.no_connection_error)
}