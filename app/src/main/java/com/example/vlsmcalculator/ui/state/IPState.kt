package com.example.vlsmcalculator.ui.state

data class IPState(
    val firstOctet : String = "",
    val secondOctet : String = "",
    val thirdOctet : String = "",
    val forthOctet : String = "",
    val subnetMask : String = ""
)