package com.nm.domain.usecase

import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.ResultResponse

interface SimulateUseCase {
    suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate>
}