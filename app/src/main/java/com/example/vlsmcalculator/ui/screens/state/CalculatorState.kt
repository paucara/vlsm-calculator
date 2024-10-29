package com.example.vlsmcalculator.ui.screens.state

data class CalculatorState(
    val firstOctet : String = "",
    val secondOctet : String = "",
    val thirdOctet : String = "",
    val forthOctet : String = "",
    val subnetMask : String = ""
)