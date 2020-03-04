package com.nm.data.repository

import com.nm.domain.entity.Simulate
import com.nm.domain.repository.SimulateRepository
import com.nm.infrastructure.net.ResultResponse

class SimulateRepositoryImpl(
    private val simulateRemoteDataSource: SimulateRemoteDataSource
) : SimulateRepository {
    override suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate> {
        return simulateRemoteDataSource.showSimulation(
            investedAmount,
            index,
            rate,
            isTaxFree,
            maturityDate
        )
    }
}