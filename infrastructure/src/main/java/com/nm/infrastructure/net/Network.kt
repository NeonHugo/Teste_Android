package com.nm.infrastructure.net

interface Network  {
    fun hasActiveInternetConnection(): Boolean
}