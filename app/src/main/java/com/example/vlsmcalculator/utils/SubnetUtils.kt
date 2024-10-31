package com.example.vlsmcalculator.utils

import com.example.vlsmcalculator.domain.model.IP
import com.example.vlsmcalculator.ui.state.ResultState
import kotlin.math.pow

fun calculateSubnet(ip: IP): ResultState {

    val firstOctet = ip.firstOctet
    val secondOctet = ip.secondOctet
    val thirdOctet = ip.thirdOctet
    val forthOctet = ip.forthOctet
    val subnetMaskPrefix = ip.subnetMask


    val subnetMask = prefixToSubnetMask(subnetMaskPrefix)


    val networkAddress =
        calculateNetworkAddress(firstOctet, secondOctet, thirdOctet, forthOctet, subnetMask)


    val broadcastAddress = calculateBroadcastAddress(networkAddress, subnetMask)


    val firstUsableHost = calculateFirstUsableHost(networkAddress)
    val lastUsableHost = calculateLastUsableHost(broadcastAddress)


    val wildcardMask = calculateWildcardMask(subnetMask)


    val usableHosts = calculateUsableHosts(subnetMaskPrefix)

    return ResultState(
        networkAddress = networkAddress,
        firstUsableHost = firstUsableHost,
        lastUsableHost = lastUsableHost,
        broadcastAddress = broadcastAddress,
        subnetMask = subnetMask.joinToString("."),
        wildcardMask = wildcardMask.joinToString("."),
        usableHosts = usableHosts.toString()
    )
}


fun prefixToSubnetMask(prefix: Int): List<Int> {
    val mask = (0..31).map { if (it < prefix) 1 else 0 }.chunked(8)
    return mask.map { octet -> octet.joinToString("").toInt(2) }
}


fun calculateNetworkAddress(
    first: Int,
    second: Int,
    third: Int,
    forth: Int,
    subnetMask: List<Int>
): String {
    val networkAddress = listOf(
        first and subnetMask[0],
        second and subnetMask[1],
        third and subnetMask[2],
        forth and subnetMask[3]
    )
    return networkAddress.joinToString(".")
}


fun calculateBroadcastAddress(networkAddress: String, subnetMask: List<Int>): String {
    val networkOctets = networkAddress.split(".").map { it.toInt() }
    val invertedMask = subnetMask.map { it.inv() and 0xFF }
    val broadcastAddress = networkOctets.zip(invertedMask).map { (networkOctet, mask) ->
        networkOctet or mask
    }
    return broadcastAddress.joinToString(".")
}


fun calculateFirstUsableHost(networkAddress: String): String {
    val networkOctets = networkAddress.split(".").map { it.toInt() }
    val firstHost = networkOctets.toMutableList()
    firstHost[3] += 1
    return firstHost.joinToString(".")
}


fun calculateLastUsableHost(broadcastAddress: String): String {
    val broadcastOctets = broadcastAddress.split(".").map { it.toInt() }
    val lastHost = broadcastOctets.toMutableList()
    lastHost[3] -= 1
    return lastHost.joinToString(".")
}


fun calculateWildcardMask(subnetMask: List<Int>): List<Int> {
    return subnetMask.map { it.inv() and 0xFF }
}


fun calculateUsableHosts(prefix: Int): Int {
    val totalHosts = 2.0.pow(32 - prefix).toInt()
    return totalHosts - 2
}
