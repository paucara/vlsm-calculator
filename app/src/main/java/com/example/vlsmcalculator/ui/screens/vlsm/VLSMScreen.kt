package com.example.vlsmcalculator.ui.screens.vlsm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vlsmcalculator.domain.model.Network
import com.example.vlsmcalculator.ui.components.CalculatorCard
import com.example.vlsmcalculator.ui.components.CalculatorTopComponent
import com.example.vlsmcalculator.ui.components.ConfigureButton
import com.example.vlsmcalculator.ui.components.NetworkDialog
import com.example.vlsmcalculator.ui.screens.state.CalculatorState

@Composable
fun VLSMScreen(viewModel: VLSMViewModel) {
    val calculatorState = viewModel.calculatorState.value
    val networks = viewModel.networkState.value
    val uiState = viewModel.uiState
    Column {
        VLSMCalculator(
            calculatorState,
            uiState,
            networks,
            viewModel::clean,
            viewModel::update,
            viewModel::addNetwork,
            viewModel::updateNetworks,
            viewModel::check
        )
    }
}

@Composable
fun VLSMCalculator(
    calculatorState: CalculatorState,
    uiState : MutableState<List<CalculatorState>>,
    networks: List<Network>,
    clean: () -> Unit,
    update: (CalculatorState) -> Unit,
    addNetwork : (Network) -> Unit,
    updateNetworks: (Int, String) -> Unit,
    check: () -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }

    CalculatorCard {
        CalculatorTopComponent(calculatorState, clean, update, check)
        ConfigureButton(openDialog)
        if (openDialog.value) {
            NetworkDialog(
                onDismissRequest = { openDialog.value = false },
                addNetwork = addNetwork,
                networks = networks,
                updateNetworks = updateNetworks,
                check = check
            )
        }
    }
    LazyColumn {
        items(uiState.value){ item ->
            NetworkResult(item)
        }
    }
}

@Composable
fun NetworkResult(calculatorState: CalculatorState){

    val value =
        calculatorState.firstOctet + "." + calculatorState.secondOctet + "." + calculatorState.thirdOctet + "." + calculatorState.forthOctet + "/" + calculatorState.subnetMask


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(text = value, modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp))
    }
}