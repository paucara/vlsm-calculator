package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.vlsmcalculator.ui.state.NetworkState

@Composable
fun NetworkDialog(
    onDismissRequest: () -> Unit,
    addNetwork: (NetworkState) -> Unit,
    deleteNetwork : (Int) -> Unit,
    networks: List<NetworkState>,
    updateNetworks: (Int, String) -> Unit,
    check: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                item {
                    TopNetworkDialog()
                }

                itemsIndexed(networks){index, item ->
                    NetworkInfo(item, updateNetworks, index, check, deleteNetwork)
                }

                item {
                    BottomNetworkDialog(onDismissRequest,addNetwork)
                }

            }
        }
    }
}

@Composable
fun TopNetworkDialog() {
    Row {
        Text(
            text = "Add networks",
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun BottomNetworkDialog(onDismissRequest: () -> Unit, addNetwork: (NetworkState) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = { onDismissRequest() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Dismiss")
        }
        TextButton(
            onClick = { addNetwork(NetworkState("", "")) },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Add network")
        }
    }
}

@Composable
fun NetworkInfo(network: NetworkState, updateNetworks: (Int, String) -> Unit, index: Int, check: () -> Unit, deleteNetwork: (Int) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Network ${index + 1}",
            modifier = Modifier.padding(8.dp).weight(1.5F),
        )
        TextField(
            value = network.requestedHosts,
            onValueChange = {
                updateNetworks(index, it)
                check()
            },
            modifier = Modifier.weight(1F),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextButton(
            onClick = { deleteNetwork(index) },
            modifier = Modifier.padding(8.dp).weight(1F),
        ) {
            Text("Delete")
        }
    }
}