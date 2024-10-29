package com.example.vlsmcalculator.domain.model

data class Subnet(
    val networkAddress: String = "",
    val firstUsableHost : String  = "",
    val lastUsableHost: String = "",
    val broadcastAddress : String = "",
    val subnetMask : String = "",
    val wildcardMask : String = "",
    val usableHosts : String = "",
)