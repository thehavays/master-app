package com.vestel.tv.factory.ui.mainmenu

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vestel.tv.factory.ui.composable.MenuHeader
import com.vestel.tv.factory.ui.theme.AlertDialogExample
import com.vestel.tv.factory.ui.theme.MenuRow
import com.vestel.tv.factory.util.Constants
import com.vestel.tv.factory.util.NavDestinations
import kotlin.system.exitProcess

@Composable
fun MainMenuActivity(
    navController: NavController
) {
    Column {
        MenuHeader("Factory Menu")
        FactoryMenuList(navController)
    }
    BackHandler {
        navController.popBackStack()
    }
}

@Composable
fun FactoryMenuList(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) AlertDialogExample(
        onDismissRequest = { showDialog = false },
        onConfirmation = {
            //TODO:
        },
        dialogTitle = "Are you sure?",
        dialogText = "All data will be deleted. Are you sure to reset device?",
        icon = Icons.Default.Info
    )

    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = Constants.MAIN_MENU_ITEMS) { name ->
            MenuRow(name = name) {
                when (name) {
                    "Device Info" -> {
                        navController.navigate(NavDestinations.DEVICE_INFO)
                    }

                    "Factory Exit" -> {
                        exitProcess(0)
                    }

                    "Factory Reset" -> {
                        showDialog = true
                    }

                    else -> {
                        Toast.makeText(context, "Not Implemented", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}