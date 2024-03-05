package com.vestel.tv.masterapp.ui.diagnostic

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vestel.tv.masterapp.ui.composable.MenuHeader

@Composable
fun DiagnosticActivity(
    navController: NavController
) {

    Column {
        MenuHeader("Diagnostic")
    }
    BackHandler {
        navController.popBackStack()
    }
}