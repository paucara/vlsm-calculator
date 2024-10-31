package com.example.vlsmcalculator.ui.screens.vlsm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vlsmcalculator.domain.mapper.IPMapper
import com.example.vlsmcalculator.domain.mapper.NetworkMapper
import com.example.vlsmcalculator.domain.usecase.CalculateVLSMUseCase
import com.example.vlsmcalculator.ui.state.IPState
import com.example.vlsmcalculator.ui.state.NetworkState

class VLSMViewModel : ViewModel() {

    private val useCase = CalculateVLSMUseCase()
    private val ipMapper = IPMapper()
    private val networkMapper = NetworkMapper()

    var ipState = mutableStateOf(IPState())
    var networksState : MutableState<List<NetworkState>> = mutableStateOf(emptyList())
    var resultState : MutableState<List<IPState>> = mutableStateOf(emptyList())

    fun clean() {
        ipState.value = IPState()
        resultState.value = emptyList()
    }

    fun update(ipState: IPState) {
        this.ipState.value = ipState
    }

    fun addNetwork(networkState: NetworkState){
        this.networksState.value += networkState
    }

    fun updateNetworks(index : Int, requestedHosts : String){
        val networks : MutableList<NetworkState> = networksState.value.toMutableList()
        networks[index] = networks[index].copy(requestedHosts = requestedHosts)
        networksState.value = networks
    }

    fun deleteNetwork(index : Int){
        val networks : MutableList<NetworkState> = networksState.value.toMutableList()
        networks.removeAt(index)
        networksState.value = networks
        check()
    }

    fun check() {

        val octets = listOf(
            ipState.value.firstOctet,
            ipState.value.secondOctet,
            ipState.value.thirdOctet,
            ipState.value.forthOctet,
            ipState.value.subnetMask
        )

        val hosts = networksState.value.map { it.requestedHosts }
        val isEmpty = hosts.any { it == "" }

        if (octets.all { it.isNotEmpty() } && !isEmpty) {
            resultState.value = useCase.execute(
                ip = ipMapper.map(ipState.value),
                networks = networksState.value.map { networkState -> networkMapper.map(networkState) }
            )
        } else {
            resultState.value = emptyList()
        }

    }
}