package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfigureButton(openDialog: MutableState<Boolean>) {
    HorizontalDivider(modifier = Modifier.padding(top = 10.dp))
    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxSize()) {
        TextButton(
            onClick = { openDialog.value = true }
        ) {
            Text("Configure network sizes")
        }
    }

}
