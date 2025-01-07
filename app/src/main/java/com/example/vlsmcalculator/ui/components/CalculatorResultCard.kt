package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorResultCard(title : String, value : String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(text = title, modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp))
        Text(text = value, modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp))
    }
}

@Composable
fun CalculatorResultCard(title : String, value : String, containerColor: Color, contentColor: Color){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = title, modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp))
        Text(text = value, modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp))
    }
}