package com.example.vlsmcalculator.ui.screens.subnet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vlsmcalculator.ui.components.CalculatorCard
import com.example.vlsmcalculator.ui.components.CalculatorResultCard
import com.example.vlsmcalculator.ui.components.CalculatorSlider
import com.example.vlsmcalculator.ui.components.CalculatorTopComponent
import com.example.vlsmcalculator.ui.screens.state.CalculatorState

@Composable
fun SubnetScreen(viewModel: SubnetViewModel) {

    val columnScroll = rememberScrollState()

    val calculatorState = viewModel.calculatorState.value
    Column {
        SubnetCalculator(calculatorState, viewModel::clean, viewModel::update, viewModel::check)
        viewModel.uiState.value?.let { subnet ->
            Column(modifier = Modifier.verticalScroll(columnScroll)) {
                CalculatorResultCard("Network Address", subnet.networkAddress)
                CalculatorResultCard("First usable host", subnet.firstUsableHost)
                CalculatorResultCard("Last usable host", subnet.lastUsableHost)
                CalculatorResultCard("Broadcast", subnet.broadcastAddress)
                CalculatorResultCard("Subnet mask", subnet.subnetMask)
                CalculatorResultCard("Wildcard mask", subnet.wildcardMask)
                CalculatorResultCard("Usable host", subnet.usableHosts)
            }
        }
    }
}


@Composable
fun SubnetCalculator(
    calculatorState: CalculatorState,
    clean: () -> Unit,
    update: (CalculatorState) -> Unit,
    check: () -> Unit
) {
    CalculatorCard {
        CalculatorTopComponent(calculatorState, clean, update, check)
        CalculatorSlider(update, calculatorState, check)
    }
}







