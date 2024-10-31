package com.example.vlsmcalculator.domain.usecase

import com.example.vlsmcalculator.domain.model.IP
import com.example.vlsmcalculator.ui.state.ResultState
import com.example.vlsmcalculator.utils.calculateSubnet

class CalculateSubnetUseCase {
    fun execute(ip : IP): ResultState {
        return calculateSubnet(ip)
    }
}