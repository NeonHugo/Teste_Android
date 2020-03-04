package com.nm.data.repository

import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.ResultResponse

interface SimulateRemoteDataSource {
    suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate>
}
