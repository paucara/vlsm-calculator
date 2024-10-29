package com.example.vlsmcalculator.ui.screens.subnet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vlsmcalculator.domain.model.Subnet
import com.example.vlsmcalculator.domain.usecase.CalculateSubnetUseCase
import com.example.vlsmcalculator.ui.screens.state.CalculatorState

class SubnetViewModel : ViewModel() {

    private val useCase = CalculateSubnetUseCase()

    var calculatorState = mutableStateOf(CalculatorState())

    val uiState = mutableStateOf(null as Subnet?)

    fun clean() {
        calculatorState.value = CalculatorState()
        uiState.value = null
    }

    fun update(calculatorState: CalculatorState) {
        this.calculatorState.value = calculatorState
    }

    fun check() {
        val octets = listOf(
            calculatorState.value.firstOctet,
            calculatorState.value.secondOctet,
            calculatorState.value.thirdOctet,
            calculatorState.value.forthOctet,
            calculatorState.value.subnetMask
        )
        if (octets.all { it.isNotEmpty() }) {
            uiState.value = useCase.execute(calculatorState.value)
        } else {
            uiState.value = null
        }
    }
}

