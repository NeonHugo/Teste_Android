package com.nm.domain.usecase

import com.nm.domain.entity.Simulate
import com.nm.domain.repository.SimulateRepository
import com.nm.infrastructure.net.ResultResponse

class SimulateUseCaseImpl(
    private val simulateRepository: SimulateRepository
) : SimulateUseCase {

    override suspend fun showSimulation(
        investedAmount: String,
        index: String,
        rate: String,
        isTaxFree: String,
        maturityDate: String
    ): ResultResponse<Simulate> {
        return simulateRepository.showSimulation(
            investedAmount,
            index,
            rate,
            isTaxFree,
            maturityDate
        )
    }
}