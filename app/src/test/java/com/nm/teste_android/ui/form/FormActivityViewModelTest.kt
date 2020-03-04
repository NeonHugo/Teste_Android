package com.nm.teste_android.ui.form

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nm.domain.entity.InvestmentParameter
import com.nm.domain.entity.Simulate
import com.nm.domain.usecase.SimulateUseCase
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.SuccessResponse
import com.nm.infrastructure.util.extensions.livedata.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class FormActivityViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @MockK
    lateinit var network: Network

    @MockK
    lateinit var simulateUseCase: SimulateUseCase

    private lateinit var formActivityViewModel: FormActivityViewModel

    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)

        MockKAnnotations.init(this)

        startKoin {
        }

        formActivityViewModel = FormActivityViewModel(simulateUseCase, network)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        stopKoin()
    }


    @Test
    fun successfulSimulation() {
        coEvery {
            simulateUseCase.showSimulation(
                "32323.0",
                "CDI",
                "123",
                "false",
                "2023-03-03"
            )
        } returns SuccessResponse(
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
        )

        formActivityViewModel.onInvestedAmountTextChanged("32323.0")
        formActivityViewModel.onMaturityDateTextChanged("03/03/2023")
        formActivityViewModel.onRateTextChanged("123")
        formActivityViewModel.onSimulation()

        val simulate = formActivityViewModel.simulation.getOrAwaitValue()

        assertEquals(Simulate(
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
        ), simulate)
    }
}