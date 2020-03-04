package com.nm.data.di

import com.nm.data.mapper.InvestmentParameterResponseToInvestmentParameterMapper
import com.nm.data.mapper.SimulationResponseToSimulationMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {

    const val investmentParameterResponseToInvestmentParameter =
        "InvestmentParameterResponseToInvestmentParameterMapper"
    const val simulationResponseToSimulation =
        "SimulationResponseToSimulationMapper"

    val mapperModules = module {
        single(named(investmentParameterResponseToInvestmentParameter)) { InvestmentParameterResponseToInvestmentParameterMapper() }
        single(named(simulationResponseToSimulation)) { SimulationResponseToSimulationMapper() }
    }

}