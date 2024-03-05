package com.vestel.tv.masterapp.ui.deviceinfo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vestel.tv.masterapp.ui.composable.MenuHeader

@Composable
fun DeviceInfoActivity(
    navController: NavController
) {

    Column {
        MenuHeader("Device Info")
    }
    BackHandler {
        navController.popBackStack()
    }
}