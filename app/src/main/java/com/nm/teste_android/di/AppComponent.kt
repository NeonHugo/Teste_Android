package com.nm.teste_android.di

import com.nm.data.di.MapperModules.mapperModules
import com.nm.teste_android.di.DataModules.dataModules
import com.nm.teste_android.di.DataModules.dataSourceModules
import com.nm.teste_android.di.DataModules.repositoryModules
import com.nm.teste_android.di.DataModules.serviceModules
import com.nm.teste_android.di.DataModules.useCaseModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getDataModules())

    private fun getDataModules(): Array<Module> {
        return arrayOf(serviceModules, dataModules, dataSourceModules, repositoryModules, useCaseModules, mapperModules)
    }
}
