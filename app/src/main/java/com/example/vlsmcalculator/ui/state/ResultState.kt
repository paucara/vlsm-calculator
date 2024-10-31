package com.example.vlsmcalculator.ui.state

data class ResultState(
    val networkAddress: String = "",
    val firstUsableHost : String  = "",
    val lastUsableHost: String = "",
    val broadcastAddress : String = "",
    val subnetMask : String = "",
    val wildcardMask : String = "",
    val usableHosts : String = "",
)