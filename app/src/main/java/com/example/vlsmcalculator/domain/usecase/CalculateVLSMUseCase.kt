package com.example.vlsmcalculator.domain.usecase

import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.ui.screens.state.CalculatorState
import com.example.vlsmcalculator.utils.calculateVLSM

class CalculateVLSMUseCase {
    fun execute(ip : CalculatorState, networks : List<Network>): List<CalculatorState> {
        return calculateVLSM(ip, networks)
    }
}