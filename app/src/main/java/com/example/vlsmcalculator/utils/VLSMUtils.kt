package com.example.vlsmcalculator.utils

import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.ui.screens.state.CalculatorState
import kotlin.math.pow

fun calculateVLSM(ip: CalculatorState, networks: List<Network>): List<CalculatorState> {
    val sortedNetworks = networks.sortedByDescending { it.requestedHosts }
    val baseIP = listOf(ip.firstOctet.toInt(), ip.secondOctet.toInt(), ip.thirdOctet.toInt(), ip.forthOctet.toInt())

    var currentIP = baseIP
    val subnets = mutableListOf<CalculatorState>()

    sortedNetworks.forEach { network ->
        val requiredHosts = network.requestedHosts + 2
        val subnetPrefix = calculateSubnetPrefix(requiredHosts.toInt())

        val newSubnet = CalculatorState(
            firstOctet = currentIP[0].toString(),
            secondOctet = currentIP[1].toString(),
            thirdOctet = currentIP[2].toString(),
            forthOctet = currentIP[3].toString(),
            subnetMask = "/$subnetPrefix"
        )

        subnets.add(newSubnet)

        currentIP = getNextNetworkAddress(currentIP, subnetPrefix)
    }

    return subnets
}


fun calculateSubnetPrefix(requiredHosts: Int): Int {
    var hosts = 1
    var prefix = 32
    while (hosts < requiredHosts) {
        hosts *= 2
        prefix--
    }
    return prefix
}


fun getNextNetworkAddress(currentIP: List<Int>, subnetPrefix: Int): List<Int> {
    val hostBits = 32 - subnetPrefix
    val increment = 2.0.pow(hostBits).toInt()

    val ipAsInt = (currentIP[0] shl 24) or (currentIP[1] shl 16) or (currentIP[2] shl 8) or currentIP[3]

    val nextIpAsInt = ipAsInt + increment

    return listOf(
        (nextIpAsInt shr 24) and 0xFF,
        (nextIpAsInt shr 16) and 0xFF,
        (nextIpAsInt shr 8) and 0xFF,
        nextIpAsInt and 0xFF
    )
}
