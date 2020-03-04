package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Simulate(
    val investmentParameter: InvestmentParameter? = null,
    val grossAmount: Double = 0.0,
    val taxesAmount: Double = 0.0,
    val netAmount: Double = 0.0,
    val grossAmountProfit: Double = 0.0,
    val netAmountProfit: Double = 0.0,
    val annualGrossRateProfit: Double = 0.0,
    val monthlyGrossRateProfit: Double = 0.0,
    val dailyGrossRateProfit: Double = 0.0,
    val taxesRate: Double = 0.0,
    val rateProfit: Double = 0.0,
    val annualNetRateProfit: Double = 0.0
) : Parcelable