package com.nm.teste_android.ui.form

import androidx.lifecycle.LiveData
import com.nm.domain.entity.Simulate
import com.nm.domain.usecase.SimulateUseCase
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.SuccessResponse
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData
import java.text.SimpleDateFormat
import java.util.*

class FormActivityViewModel(
    private val simulateUseCase: SimulateUseCase,
    network: Network
) : BaseViewModel(network) {

    private val _simulation =
        FlexibleLiveData<Simulate>()

    val simulation: LiveData<Simulate> get() = _simulation

    val enableSimulateButton: LiveData<Boolean> get() = formActivityPre.enableSimulateButton

    private val formActivityPre = FormActivityPre()

    override fun doOnError(throwable: Throwable) {
        _loading.value = false
        _empty.value = true
        _error.value = true
    }

    fun onSimulation(
//        investedAmount: String,
//        index: String,
//        rate: String,
//        isTaxFree: String,
//        maturityDate: String
    ) {
        launchDataLoad {
            _empty.value = false

            when (val request = simulateUseCase.showSimulation(
                formActivityPre.investedAmount,
                formActivityPre.index,
                formActivityPre.rate,
                formActivityPre.isTaxFree,
                formatDate(formActivityPre.maturityDate)
            )) {
                is SuccessResponse -> {
                    _empty.value = false
                    _simulation.value = request.body
                }
                is ErrorResponse -> {
                    _empty.value = true
                }
                else -> {
                    _empty.value = true
                }
            }
        }
    }

    fun onInvestedAmountTextChanged(text: CharSequence?) {
        text?.run {
            formActivityPre.setInvestedAmount(toString())
        }
    }

    fun onMaturityDateTextChanged(text: CharSequence?) {
        text?.run {
            formActivityPre.setMaturityDate(toString())
        }
    }

    fun onRateTextChanged(text: CharSequence?) {
        text?.run {
            formActivityPre.setRate(toString())
        }
    }

    private fun formatDate(date: String): String {
        val formatoE = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val formatoS = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
        val dateE = formatoE.parse(date)
        return formatoS.format(dateE!!)
    }
}