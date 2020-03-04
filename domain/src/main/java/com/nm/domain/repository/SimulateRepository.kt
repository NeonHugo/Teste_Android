package com.nm.domain.repository

import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.ResultResponse

interface SimulateRepository {
    suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate>
}