package com.nm.teste_android.ui.form

import com.nm.infrastructure.util.extensions.format.toSafeDouble
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData

class FormActivityPre {
    var investedAmount: String = ""
        private set
    var index: String = "CDI"
        private set
    var rate: String = ""
        private set
    var isTaxFree: String = "false"
        private set
    var maturityDate: String = ""
        private set
    var enableSimulateButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
        private set

    fun setInvestedAmount(investedAmount: String) {
        this.investedAmount = investedAmount
        shouldEnableSimulateButton()
    }

    fun setIndex(index: String) {
        this.index = index
        shouldEnableSimulateButton()
    }

    fun setRate(rate: String) {
        this.rate = rate
        shouldEnableSimulateButton()
    }

    fun setIsTaxFree(isTaxFree: String) {
        this.isTaxFree = isTaxFree
        shouldEnableSimulateButton()
    }

    fun setMaturityDate(maturityDate: String) {
        this.maturityDate = maturityDate
        shouldEnableSimulateButton()
    }

    fun investedAmountIsValid(): Boolean {
        return investedAmount.isNotEmpty() && investedAmount.toSafeDouble() > 0.0
    }

    fun rateIsValid(): Boolean {
        return rate.isNotEmpty() && rate.toSafeDouble() > 0.0
    }

    fun maturityDateIsValid(): Boolean {
        return maturityDate.isNotEmpty()
    }

    private fun personalFormValidation(): Boolean {
        return investedAmountIsValid() &&
                rateIsValid() &&
                maturityDateIsValid()
    }

    private fun shouldEnableSimulateButton() {

        enableSimulateButton.value = personalFormValidation()
    }

    companion object {
        private val DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(19|20)\\d\\d\$".toRegex()
    }

}