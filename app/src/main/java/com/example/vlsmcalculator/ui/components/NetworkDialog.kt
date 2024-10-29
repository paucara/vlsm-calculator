package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.vlsmcalculator.domain.model.Network

@Composable
fun NetworkDialog(
    onDismissRequest: () -> Unit,
    addNetwork: (Network) -> Unit,
    networks: List<Network>,
    updateNetworks: (Int, String) -> Unit,
    onConfirmation: () -> Unit = {},
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
                    TopNetworkDialog(addNetwork)
                }

                itemsIndexed(networks){index, item ->
                    NetworkInfo(item, updateNetworks, index, check)
                }


                item {
                    BottomNetworkDialog(onDismissRequest, onConfirmation)
                }

            }
        }
    }
}

@Composable
fun TopNetworkDialog(addNetwork: (Network) -> Unit){
    Row {
        Text(
            text = "Add networks",
            modifier = Modifier.padding(16.dp),
        )
        IconButton(
            onClick = { addNetwork(Network("",0)) },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Composable
fun BottomNetworkDialog(onDismissRequest: () -> Unit, onConfirmation: () -> Unit){
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
            onClick = { onConfirmation() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Confirm")
        }
    }
}

@Composable
fun NetworkInfo(network: Network, updateNetworks: (Int, String) -> Unit, index: Int,check: () -> Unit){
    Row {
        TextField(
            value = if(network.requestedHosts == 0L) "" else network.requestedHosts.toString(),
            onValueChange = {
                updateNetworks(index, it)
                check()
            }
        )
    }
}