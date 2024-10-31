package com.example.vlsmcalculator.ui.screens.subnet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vlsmcalculator.domain.mapper.IPMapper
import com.example.vlsmcalculator.ui.state.ResultState
import com.example.vlsmcalculator.domain.usecase.CalculateSubnetUseCase
import com.example.vlsmcalculator.ui.state.IPState

class SubnetViewModel : ViewModel() {

    private val useCase = CalculateSubnetUseCase()
    private val ipMapper = IPMapper()

    var ipState = mutableStateOf(IPState())

    val resultState = mutableStateOf(null as ResultState?)

    fun clean() {
        ipState.value = IPState()
        resultState.value = null
    }

    fun update(ipState: IPState) {
        this.ipState.value = ipState
    }

    fun check() {
        val octets = listOf(
            ipState.value.firstOctet,
            ipState.value.secondOctet,
            ipState.value.thirdOctet,
            ipState.value.forthOctet,
            ipState.value.subnetMask
        )
        if (octets.all { it.isNotEmpty() }) {
            val ip = ipMapper.map(ipState.value)
            resultState.value = useCase.execute(ip)
        } else {
            resultState.value = null
        }
    }
}

