package com.example.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CalculatorField(placeholder : String, modifier: Modifier, value: String, onValueChange: (String) -> Unit, check : () -> Unit) {
    val color = MaterialTheme.colorScheme.secondary
    Box(
        modifier = modifier
    ) {
        if (value.isEmpty()) {
            Text(
                modifier = modifier,
                text = placeholder,
                style =  MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        }
        BasicTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = value,
            onValueChange = { onValueChange(it)
                            check() },
            modifier = modifier
                .drawBehind {
                    val strokeWidth = 2f
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = color,
                        start = Offset(0f, y),
                        end = Offset(size.width / 1.5F, y),
                        strokeWidth = strokeWidth
                    )
                },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
        )
    }
}