package com.nm.infrastructure.net

import com.google.gson.Gson
import com.nm.infrastructure.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuild {

    const val NO_AUTHENTICATION = "No-Authentication"
    const val NO_AUTHENTICATION_HEADER = "No-Authentication: true"

    inline fun <reified T> makeService(base_url: String): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(getLogLevel()))
        }.build().let { retrofitCreate(base_url, it) }
    }

    inline fun <reified T> makeService(base_url: HttpUrl): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(getLogLevel()))
        }.build().let { retrofitCreate(base_url, it) }
    }

    inline fun <reified T> makeLoggedService(base_url: String, interceptor: Interceptor): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(getLogLevel()))
        }.build().let { retrofitCreate(base_url, it) }
    }

    fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    inline fun <reified T> retrofitCreate(base_url: String, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(T::class.java)
    }

    inline fun <reified T> retrofitCreate(base_url: HttpUrl, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(T::class.java)
    }

}