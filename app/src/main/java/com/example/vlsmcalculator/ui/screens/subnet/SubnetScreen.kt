package com.example.vlsmcalculator.ui.screens.subnet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vlsmcalculator.ui.state.ResultState
import com.example.vlsmcalculator.ui.components.CalculatorCard
import com.example.vlsmcalculator.ui.components.CalculatorResultCard
import com.example.vlsmcalculator.ui.components.CalculatorSlider
import com.example.vlsmcalculator.ui.components.CalculatorTopComponent
import com.example.vlsmcalculator.ui.state.IPState

@Composable
fun SubnetScreen(viewModel: SubnetViewModel) {

    val ipState = viewModel.ipState.value
    val resultState = viewModel.resultState.value

    Column {
        SubnetCalculator(ipState, viewModel::clean, viewModel::update, viewModel::check)
        resultState?.let { result -> SubnetCalculatorResult(result) }
    }
}


@Composable
fun SubnetCalculator(
    ipState: IPState,
    clean: () -> Unit,
    update: (IPState) -> Unit,
    check: () -> Unit
) {
    CalculatorCard {
        CalculatorTopComponent(ipState, clean, update, check)
        CalculatorSlider(update, ipState, check)
    }
}

@Composable
fun SubnetCalculatorResult(
    result: ResultState
) {
    val columnScroll = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(columnScroll)) {
        CalculatorResultCard("Network Address", result.networkAddress)
        CalculatorResultCard("First usable host", result.firstUsableHost)
        CalculatorResultCard("Last usable host", result.lastUsableHost)
        CalculatorResultCard("Broadcast", result.broadcastAddress)
        CalculatorResultCard("Subnet mask", result.subnetMask)
        CalculatorResultCard("Wildcard mask", result.wildcardMask)
        CalculatorResultCard("Usable host", result.usableHosts)
    }
}






