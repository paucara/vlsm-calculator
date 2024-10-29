package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.example.vlsmcalculator.ui.screens.state.CalculatorState

@Composable
fun CalculatorTopComponent(
    uiState: CalculatorState,
    clean: () -> Unit,
    update: (CalculatorState) -> Unit,
    check: () -> Unit
) {
    Row(modifier = Modifier.padding(15.dp)) {
        Column(modifier = Modifier.weight(3F)) {

            val focusRequesters = remember { List(5) { FocusRequester() } }

            Text(text = "IPv4 network address", modifier = Modifier.padding(bottom = 10.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                CalculatorField(
                    modifier = Modifier
                        .weight(1F)
                        .focusRequester(focusRequesters[0]),
                    value = uiState.firstOctet,
                    placeholder = "192",
                    check = check,
                    onValueChange = { newValue ->
                        if (newValue.length <= 3) {
                            update(uiState.copy(firstOctet = newValue))
                            if (newValue.length == 3) {
                                focusRequesters[1].requestFocus()
                            }
                        } else {
                            focusRequesters[1].requestFocus()
                        }
                    }
                )
                Text(
                    text = ".", modifier = Modifier.weight(.2F)
                )
                CalculatorField(
                    modifier = Modifier
                        .weight(1F)
                        .focusRequester(focusRequesters[1]),
                    value = uiState.secondOctet,
                    placeholder = "168",
                    check = check,
                    onValueChange = { newValue ->
                        if (newValue.length <= 3) {
                            update(uiState.copy(secondOctet = newValue))
                            if (newValue.length == 3) {
                                focusRequesters[2].requestFocus()
                            }
                        } else {
                            focusRequesters[2].requestFocus()
                        }
                    }
                )
                Text(
                    text = ".", modifier = Modifier.weight(.2F)
                )
                CalculatorField(
                    modifier = Modifier
                        .weight(1F)
                        .focusRequester(focusRequesters[2]),
                    value = uiState.thirdOctet,
                    placeholder = "0",
                    check = check,
                    onValueChange = {
                            newValue ->
                        if (newValue.length <= 3) {
                            update(uiState.copy(thirdOctet = newValue))
                            if(newValue.length == 3){
                                focusRequesters[3].requestFocus()
                            }
                        } else {
                            focusRequesters[3].requestFocus()
                        }
                    }
                )
                Text(
                    text = ".", modifier = Modifier.weight(.2F)
                )
                CalculatorField(
                    modifier = Modifier
                        .weight(1F)
                        .focusRequester(focusRequesters[3]),
                    value = uiState.forthOctet,
                    placeholder = "1",
                    check = check,
                    onValueChange = {
                            newValue ->
                        if (newValue.length <= 3) {
                            update(uiState.copy(forthOctet = newValue))
                            if(newValue.length == 3){
                                focusRequesters[4].requestFocus()
                            }
                        } else {
                            focusRequesters[4].requestFocus()
                        }
                    }
                )
                Text(
                    text = "/", modifier = Modifier.weight(.2F)
                )
                CalculatorField(
                    modifier = Modifier
                        .weight(1F)
                        .focusRequester(focusRequesters[4]),
                    value = uiState.subnetMask,
                    placeholder = "25",
                    check = check,
                    onValueChange = {newValue ->
                        if (newValue.length <= 2) update(uiState.copy(subnetMask = newValue))}
                )
            }
        }
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            CleanButton(clean)
        }
    }
}