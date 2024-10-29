package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BinaryButton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "View as binary", modifier = Modifier.padding(10.dp).weight(4F))
            Switch(
                modifier = Modifier.weight(1F),
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}
