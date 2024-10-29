package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CleanButton(clean: () -> Unit) {
    IconButton(
        onClick = { clean() },
        modifier = Modifier
            .width(25.dp)
            .height(25.dp)
    ) { Icon(imageVector = Icons.Default.Delete, contentDescription = null) }
}