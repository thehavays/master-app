package com.vestel.tv.masterapp.ui.mainmenu

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vestel.tv.masterapp.ui.composable.MenuHeader
import com.vestel.tv.masterapp.ui.theme.AlertDialogExample
import com.vestel.tv.masterapp.ui.theme.MenuRow
import com.vestel.tv.middleware.VestelFactoryCommandManager
import kotlin.system.exitProcess

@Composable
fun MainMenuActivity(
    navController: NavController,
    menuItems: List<String>
) {

    Column {
        MenuHeader("Factory Menu")
        FactoryMenuList(menuItems, navController)
    }
    BackHandler {
        navController.popBackStack()
    }
}

@Composable
fun FactoryMenuList(greetings: List<String>, navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) AlertDialogExample(
        onDismissRequest = { showDialog.value = false },
        onConfirmation = {
            VestelFactoryCommandManager.performFactoryReset()
        },
        dialogTitle = "Are you sure?",
        dialogText = "All data will be deleted. Are you sure to reset device?",
        icon = Icons.Default.Info
    )

    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = greetings) { name ->
            MenuRow(name = name) {
                when (name) {
                    "Device Info" -> {
                        navController.navigate("DEVICE_INFO")
                    }

                    "Diagnostic" -> {
                        navController.navigate("DIAGNOSTIC")
                    }

                    "Factory Exit" -> {
                        exitProcess(0)
                    }

                    "Factory Reset" -> {
                        showDialog.value = true
                    }

                    else -> {
                        Toast.makeText(context, "Not Implemented", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}