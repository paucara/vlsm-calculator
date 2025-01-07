package com.example.vlsmcalculator.ui.screens.vlsm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.vlsmcalculator.ui.components.CalculatorCard
import com.example.vlsmcalculator.ui.components.CalculatorResultCard
import com.example.vlsmcalculator.ui.components.CalculatorTopComponent
import com.example.vlsmcalculator.ui.components.ConfigureButton
import com.example.vlsmcalculator.ui.components.NetworkDialog
import com.example.vlsmcalculator.ui.state.IPState
import com.example.vlsmcalculator.ui.state.NetworkState
import com.example.vlsmcalculator.ui.state.ResultState

@Composable
fun VLSMScreen(viewModel: VLSMViewModel) {
    val ipState = viewModel.ipState.value
    val networksState = viewModel.networksState.value
    val resultState = viewModel.resultState.value
    Column {
        VLSMCalculator(
            ipState,
            networksState,
            resultState,
            viewModel::clean,
            viewModel::update,
            viewModel::check,
            viewModel::addNetwork,
            viewModel::updateNetworks,
            viewModel::deleteNetwork
        )
    }
}

@Composable
fun VLSMCalculator(
    ipState: IPState,
    networksState: List<NetworkState>,
    resultState: List<ResultState>,
    clean: () -> Unit,
    update: (IPState) -> Unit,
    check: () -> Unit,
    addNetwork: (NetworkState) -> Unit,
    updateNetworks: (Int, String) -> Unit,
    deleteNetwork: (Int) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }

    CalculatorCard {
        CalculatorTopComponent(ipState, clean, update, check)
        ConfigureButton(openDialog)
        if (openDialog.value) {
            NetworkDialog(
                onDismissRequest = { openDialog.value = false },
                addNetwork = addNetwork,
                networks = networksState,
                updateNetworks = updateNetworks,
                check = check,
                deleteNetwork = deleteNetwork
            )
        }
    }
    val columnScroll = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(columnScroll)) {
        resultState.map {
                result -> VLSMResultCard(result)
        }
    }
}

@Composable
fun VLSMResultCard(
    result: ResultState
) {
    Column {
        CalculatorResultCard(
            "Network Address",
            result.networkAddress,
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer
        )
        CalculatorResultCard("First usable host", result.firstUsableHost)
        CalculatorResultCard("Last usable host", result.lastUsableHost)
        CalculatorResultCard("Broadcast", result.broadcastAddress)
        CalculatorResultCard("Subnet mask", result.subnetMask)
        CalculatorResultCard("Wildcard mask", result.wildcardMask)
        CalculatorResultCard("Usable host", result.usableHosts)
        HorizontalDivider()
    }
}
