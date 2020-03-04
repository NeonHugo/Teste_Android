package com.nm.teste_android.di

import com.nm.data.di.MapperModules.simulationResponseToSimulation
import com.nm.data.repository.*
import com.nm.data.services.EasynvestService
import com.nm.domain.repository.SimulateRepository
import com.nm.domain.usecase.*
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.NetworkImpl
import com.nm.infrastructure.net.RetrofitBuild.makeService
import com.nm.infrastructure.util.error.ResourcesStringError
import com.nm.infrastructure.util.error.ResourcesStringErrorImpl
import com.nm.teste_android.ui.form.FormActivityViewModel
import com.nm.teste_android.ui.simulate.SimulateActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModules {

    private const val easynvestService = "EasynvestService"

    val serviceModules = module {
        single(named(easynvestService)) { makeService<EasynvestService>("https://api-simulator-calc.easynvest.com.br/calculator/") }

        viewModel { FormActivityViewModel(get(), get()) }
        viewModel { SimulateActivityViewModel(get()) }
    }

    val dataModules = module {
        single<Network> { NetworkImpl(get()) }
        single<ResourcesStringError> { ResourcesStringErrorImpl(get()) }
    }

    val dataSourceModules = module {
        single<SimulateRemoteDataSource> {
            SimulateRemoteDataSourceImpl(
                get(named(easynvestService)),
                get(named(simulationResponseToSimulation))
            )
        }
    }

    val repositoryModules = module {
        single<SimulateRepository> { SimulateRepositoryImpl(get()) }
    }

    val useCaseModules = module {
        single<SimulateUseCase> { SimulateUseCaseImpl(get()) }
    }

}
