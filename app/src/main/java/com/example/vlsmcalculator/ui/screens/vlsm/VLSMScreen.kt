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
import com.example.vlsmcalculator.ui.components.CalculatorCard
import com.example.vlsmcalculator.ui.components.CalculatorTopComponent
import com.example.vlsmcalculator.ui.components.ConfigureButton
import com.example.vlsmcalculator.ui.components.NetworkDialog
import com.example.vlsmcalculator.ui.state.IPState
import com.example.vlsmcalculator.ui.state.NetworkState

@Composable
fun VLSMScreen(viewModel: VLSMViewModel) {
    val ipState = viewModel.ipState.value
    val networksState = viewModel.networksState.value
    val resultState = viewModel.resultState
    Column {
        VLSMCalculator(
            ipState,
            resultState,
            networksState,
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
    resultState: MutableState<List<IPState>>,
    networksState: List<NetworkState>,
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
    LazyColumn {
        items(resultState.value){ item ->
            NetworkResult(item)
        }
    }
}

@Composable
fun NetworkResult(ipState: IPState){

    val value =
        ipState.firstOctet + "." + ipState.secondOctet + "." + ipState.thirdOctet + "." + ipState.forthOctet  + "/" + ipState.subnetMask

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