package com.example.vlsmcalculator.domain.model

data class IP(
    val firstOctet : Int = 0,
    val secondOctet : Int = 0,
    val thirdOctet : Int = 0,
    val forthOctet : Int = 0,
    val subnetMask : Int = 0
)