package com.nm.teste_android

import androidx.multidex.MultiDexApplication
import com.nm.teste_android.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

open class EYApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(this@EYApplication)
            modules(getAllModules())
        }
    }

}