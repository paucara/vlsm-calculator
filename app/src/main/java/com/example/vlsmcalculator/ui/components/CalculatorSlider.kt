package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vlsmcalculator.ui.state.IPState

@Composable
fun CalculatorSlider(update: (IPState) -> Unit, uiState: IPState, check: () -> Unit) {
    val sliderPosition = remember { mutableFloatStateOf(0f) }
    HorizontalDivider(modifier = Modifier.padding(top = 10.dp))
    Slider(
        modifier = Modifier.padding(15.dp),
        value = sliderPosition.floatValue,
        onValueChange = {
            sliderPosition.floatValue = it
            update(uiState.copy(subnetMask = sliderPosition.floatValue.toInt().toString()))
            check()
        },
        valueRange = 1f..31f
    )
}