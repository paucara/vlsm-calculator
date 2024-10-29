package com.example.vlsmcalculator.domain.usecase

import com.example.vlsmcalculator.domain.model.Subnet
import com.example.vlsmcalculator.ui.screens.state.CalculatorState
import com.example.vlsmcalculator.utils.calculateSubnet

class CalculateSubnetUseCase {
    fun execute(ip : CalculatorState): Subnet {
        return calculateSubnet(ip)
    }
}