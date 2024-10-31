package com.example.vlsmcalculator.domain.usecase

import com.example.vlsmcalculator.domain.model.IP
import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.ui.state.IPState
import com.example.vlsmcalculator.utils.calculateVLSM

class CalculateVLSMUseCase {
    fun execute(ip : IP, networks : List<Network>): List<IPState> {
        return calculateVLSM(ip, networks)
    }
}