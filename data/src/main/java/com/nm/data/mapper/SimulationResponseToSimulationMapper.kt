package com.nm.data.mapper

import com.nm.data.model.SimulateResponse
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.Mapper

class SimulationResponseToSimulationMapper : Mapper<SimulateResponse, Simulate>() {
    override fun transform(item: SimulateResponse?): Simulate {
        val ipMapper = InvestmentParameterResponseToInvestmentParameterMapper()

        var simulate: Simulate? = null
        item?.let {
            simulate = Simulate(
                investmentParameter = ipMapper.transform(it.investmentParameter),
                grossAmount = it.grossAmount,
                taxesAmount = it.taxesAmount,
                netAmount = it.netAmount,
                grossAmountProfit = it.grossAmountProfit,
                netAmountProfit = it.netAmountProfit,
                annualGrossRateProfit = it.annualGrossRateProfit,
                monthlyGrossRateProfit = it.monthlyGrossRateProfit,
                dailyGrossRateProfit = it.dailyGrossRateProfit,
                taxesRate = it.taxesRate,
                rateProfit = it.rateProfit,
                annualNetRateProfit = it.netAmountProfit
            )
        }

        return simulate ?: Simulate()
    }
}