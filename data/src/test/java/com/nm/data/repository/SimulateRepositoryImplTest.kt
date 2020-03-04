package com.nm.data.repository

import com.nm.domain.entity.InvestmentParameter
import com.nm.domain.entity.Simulate
import com.nm.domain.repository.SimulateRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

class SimulateRepositoryImplTest {

    @Mock
    lateinit var simulateRemoteDataSource: SimulateRemoteDataSource

    private lateinit var simulateRepository: SimulateRepository

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        simulateRepository = SimulateRepositoryImpl(simulateRemoteDataSource)
    }

    @Test
    fun showSimulationSuccessfulResponse() = runBlocking {
        doReturn(
            createSimulation()
        ).`when`(simulateRemoteDataSource).showSimulation(
            "32323.0",
            "CDI",
            "123",
            "false",
            "2023-03-03"
        )

        val response = simulateRepository.showSimulation(
            "32323.0",
            "CDI",
            "123",
            "false",
            "2023-03-03"
        )

        assertEquals(
            SuccessResponse(
                Simulate(
                    investmentParameter = InvestmentParameter(
                        investedAmount = 32323.0,
                        yearlyInterestRate = 9.5512,
                        maturityBusinessDays = 1981,
                        maturityTotalDays = 1409,
                        maturityDate = "2023-03-03T00:00:00",
                        rate = 123.0,
                        isTaxFree = false
                    ),
                    grossAmount = 60528.20,
                    taxesAmount = 4230.78,
                    netAmount = 56297.42,
                    grossAmountProfit = 28205.20,
                    netAmountProfit = 23974.42,
                    annualGrossRateProfit = 87.26,
                    monthlyGrossRateProfit = 0.76,
                    dailyGrossRateProfit = 0.000445330025305748,
                    taxesRate = 15.0,
                    rateProfit = 9.5512,
                    annualNetRateProfit = 74.17
                )
            ), response
        )
    }

    private fun createSimulation(): ResultResponse<Simulate> {
        val simulate = Simulate(
            investmentParameter = InvestmentParameter(
                investedAmount = 32323.0,
                yearlyInterestRate = 9.5512,
                maturityBusinessDays = 1981,
                maturityTotalDays = 1409,
                maturityDate = "2023-03-03T00:00:00",
                rate = 123.0,
                isTaxFree = false
            ),
            grossAmount = 60528.20,
            taxesAmount = 4230.78,
            netAmount = 56297.42,
            grossAmountProfit = 28205.20,
            netAmountProfit = 23974.42,
            annualGrossRateProfit = 87.26,
            monthlyGrossRateProfit = 0.76,
            dailyGrossRateProfit = 0.000445330025305748,
            taxesRate = 15.0,
            rateProfit = 9.5512,
            annualNetRateProfit = 74.17
        )

        return SuccessResponse(simulate)
    }

    @Test
    fun showSimulationUnSuccessfulResponseTest() = runBlocking {
        doReturn(
            createUnSuccesfulResponse()
        ).`when`(simulateRemoteDataSource).showSimulation(
            "32323.0",
            "CDI",
            "123",
            "false",
            "2023-03-03"
        )

        val response =
            simulateRepository.showSimulation("32323.0", "CDI", "123", "false", "2023-03-03")

        assertEquals(
            ErrorResponse<ApiError>(
                ApiError(
                    10,
                    "General Error!",
                    "General Error!"
                )
            ), response
        )
    }

    private fun createUnSuccesfulResponse(): ResultResponse<ApiError> {
        return ErrorResponse(
            ApiError(
                10,
                "General Error!",
                "General Error!"
            )
        )
    }

}