package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvestmentParameter(
    val investedAmount: Double = 0.0,
    val yearlyInterestRate: Double = 0.0,
    val maturityTotalDays: Int = -1,
    val maturityBusinessDays: Int = -1,
    val maturityDate: String = "",
    val rate: Double = 0.0,
    val isTaxFree: Boolean = false
) : Parcelable