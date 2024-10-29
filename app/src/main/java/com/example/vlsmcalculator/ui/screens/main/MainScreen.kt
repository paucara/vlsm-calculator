package com.example.vlsmcalculator.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vlsmcalculator.ui.navigation.SubnetScreen
import com.example.vlsmcalculator.ui.navigation.VLSMScreen
import com.example.vlsmcalculator.ui.screens.subnet.SubnetScreen
import com.example.vlsmcalculator.ui.screens.subnet.SubnetViewModel
import com.example.vlsmcalculator.ui.screens.vlsm.VLSMScreen
import com.example.vlsmcalculator.ui.screens.vlsm.VLSMViewModel

@Composable
fun MainScreen(subnetViewModel: SubnetViewModel, vlsmViewModel: VLSMViewModel) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = SubnetScreen) {
                composable<SubnetScreen> { SubnetScreen(subnetViewModel) }
                composable<VLSMScreen> { VLSMScreen(vlsmViewModel) }
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController) {

    val items = listOf(SubnetScreen, VLSMScreen)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var state by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = state) {
        items.forEachIndexed {index, item ->
            Tab(
                selected = currentRoute == item.javaClass.name,
                onClick = {
                    navController.navigate(item) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                    state = index
                },
                text = { Text(text = item.javaClass.simpleName, maxLines = 2, overflow = TextOverflow.Ellipsis) }
            )
        }
    }
}
