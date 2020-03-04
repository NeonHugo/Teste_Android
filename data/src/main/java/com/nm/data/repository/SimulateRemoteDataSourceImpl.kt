package com.nm.data.repository

import com.nm.data.model.SimulateResponse
import com.nm.data.services.EasynvestService
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.Mapper
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.create

class SimulateRemoteDataSourceImpl(
    private val easynvestService: EasynvestService,
    private val mapper: Mapper<SimulateResponse, Simulate>
) : SimulateRemoteDataSource {

    override suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate> {
        return easynvestService.getSimulation(
            investedAmount,
            index,
            rate,
            isTaxFree,
            maturityDate
        ).create(mapper)
    }
}
