package com.example.vlsmcalculator.domain.mapper

import com.example.vlsmcalculator.domain.model.IP
import com.example.vlsmcalculator.ui.state.IPState

class IPMapper {
    fun map(ipState: IPState): IP {
        return IP(
            firstOctet = ipState.firstOctet.toInt(),
            secondOctet = ipState.secondOctet.toInt(),
            thirdOctet = ipState.thirdOctet.toInt(),
            forthOctet = ipState.forthOctet.toInt(),
            subnetMask = ipState.subnetMask.toInt()
        )
    }
}