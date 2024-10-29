package com.example.vlsmcalculator.ui.screens.vlsm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.domain.usecase.CalculateVLSMUseCase
import com.example.vlsmcalculator.ui.screens.state.CalculatorState

class VLSMViewModel : ViewModel() {

    private val useCase = CalculateVLSMUseCase()

    var calculatorState = mutableStateOf(CalculatorState())

    var networkState : MutableState<List<Network>> = mutableStateOf(emptyList())

    var uiState : MutableState<List<CalculatorState>> = mutableStateOf(emptyList())

    fun clean() {
        calculatorState.value = CalculatorState()
    }
    fun update(calculatorState: CalculatorState) {
        this.calculatorState.value = calculatorState
    }

    fun addNetwork(network: Network){
        networkState.value += network
    }

    fun updateNetworks(index : Int, requestedHosts : String){
        val list : MutableList<Network> = networkState.value.toMutableList()
        list[index] = list[index].copy(requestedHosts = if(requestedHosts.isNotEmpty()) requestedHosts.toLong() else 0)
        networkState.value = list
    }

    fun check() {



        val octets = listOf(
            calculatorState.value.firstOctet,
            calculatorState.value.secondOctet,
            calculatorState.value.thirdOctet,
            calculatorState.value.forthOctet,
            calculatorState.value.subnetMask
        )

        val hosts = networkState.value.map { it.requestedHosts }
        val hasZero = hosts.any { it == 0L }

        if (octets.all { it.isNotEmpty() } && !hasZero) {
            uiState.value = useCase.execute(
                ip = calculatorState.value,
                networks = networkState.value
            )
        } else {
            uiState.value = emptyList()
        }
    }
}