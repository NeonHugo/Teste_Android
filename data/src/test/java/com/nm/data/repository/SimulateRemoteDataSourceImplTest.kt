package com.nm.data.repository

import com.nm.data.mapper.SimulationResponseToSimulationMapper
import com.nm.domain.entity.InvestmentParameter
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.RetrofitBuild
import com.nm.infrastructure.net.RetrofitBuild.makeService
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations.initMocks

class SimulateRemoteDataSourceImplTest {
    private var mockWebServer: MockWebServer? = null

    private lateinit var simulateRemoteDataSource: SimulateRemoteDataSource

    @Before
    fun setupTest() {
        startMockWebServer()
        initMocks(this)

        simulateRemoteDataSource = SimulateRemoteDataSourceImpl(
            makeService(mockWebServer!!.url("/")),
            SimulationResponseToSimulationMapper()
        )
    }

    @After
    fun tearDown() {
        finishMockWebServer()
    }

    private fun startMockWebServer() {
        if (mockWebServer == null) {
            mockWebServer = MockWebServer()
            mockWebServer?.start(MOCK_WEB_SERVER_PORT)
            setDispatcher(EasynvestDispatcher())
        }
    }

    @Test
    fun successfulResponse() = runBlocking {
        val response = simulateRemoteDataSource.showSimulation(
            "32323.0",
            "CDI",
            "123",
            "false",
            "2023-03-03"
        )

        Assert.assertEquals(
            SuccessResponse(
                Simulate(
                    investmentParameter = InvestmentParameter(
                        investedAmount = 32323.0,
                        yearlyInterestRate = 6.367,
                        maturityBusinessDays = 1200,
                        maturityTotalDays = 1409,
                        maturityDate = "2023-03-03T00:00:00",
                        rate = 123.0,
                        isTaxFree = false
                    ),
                    grossAmount = 40860.61,
                    taxesAmount = 1280.64,
                    netAmount = 39579.97,
                    grossAmountProfit = 8537.61,
                    netAmountProfit = 22.45,
                    annualGrossRateProfit = 26.41,
                    monthlyGrossRateProfit = 0.52,
                    dailyGrossRateProfit = 0.000301314625672355,
                    taxesRate = 15.0,
                    rateProfit = 6.367,
                    annualNetRateProfit = 22.45
                )
            ), response
        )

    }

    private fun setDispatcher(dispatcher: Dispatcher) {
        mockWebServer?.dispatcher = dispatcher
    }

    private fun finishMockWebServer() {
        if (mockWebServer != null) {
            mockWebServer?.shutdown()
        }
    }


    companion object {
        const val MOCK_WEB_SERVER_PORT = 9091
    }
}