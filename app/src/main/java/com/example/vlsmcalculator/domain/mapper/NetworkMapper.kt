package com.example.vlsmcalculator.domain.mapper

import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.ui.state.NetworkState

class NetworkMapper {
    fun map(networkState: NetworkState): Network {
        return Network(
            networkName = networkState.networkName,
            requestedHosts = if(networkState.requestedHosts.isNotEmpty()) networkState.requestedHosts.toLong() else 0L
        )
    }
}