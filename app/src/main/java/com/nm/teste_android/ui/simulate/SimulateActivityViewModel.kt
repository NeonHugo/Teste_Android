package com.nm.teste_android.ui.simulate

import androidx.lifecycle.LiveData
import com.nm.domain.entity.Simulate
import com.nm.domain.usecase.SimulateUseCase
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.SuccessResponse
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData

class SimulateActivityViewModel(
    network: Network
) : BaseViewModel(network) {

    private val _simulation =
        FlexibleLiveData<Simulate>()

    val simulation: LiveData<Simulate> get() = _simulation

    override fun doOnError(throwable: Throwable) {
        _loading.value = false
        _empty.value = true
        _error.value = true
    }

    fun onShowSimulation(simulate: Simulate) {
        launchDataLoad {
            _empty.value = false
            _simulation.value = simulate
        }
    }
}