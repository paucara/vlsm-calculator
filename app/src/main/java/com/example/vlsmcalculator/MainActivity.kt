package com.example.vlsmcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.vlsmcalculator.ui.screens.main.MainScreen
import com.example.vlsmcalculator.ui.screens.subnet.SubnetViewModel
import com.example.vlsmcalculator.ui.screens.vlsm.VLSMViewModel
import com.example.vlsmcalculator.ui.theme.VLSMCalculatorTheme

class MainActivity : ComponentActivity() {

    private val subnetViewModel = SubnetViewModel()
    private val vlsmViewModel = VLSMViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VLSMCalculatorTheme {
                MainScreen(subnetViewModel, vlsmViewModel)
            }
        }
    }
}
