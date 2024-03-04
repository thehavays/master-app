package com.vestel.tv.masterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import com.vestel.tv.masterapp.ui.theme.AlertDialogExample
import com.vestel.tv.masterapp.ui.theme.MasterAppTheme
import com.vestel.tv.masterapp.ui.theme.MenuHeader
import com.vestel.tv.masterapp.ui.theme.MenuRow
import com.vestel.tv.middleware.VestelFactoryCommandManager
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAppTheme(
                setTranslucent(true),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.4f)
                        .padding(5.dp),
                    colors = NonInteractiveSurfaceDefaults.colors(
                        containerColor = Color(50, 128, 160)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column {
                        MenuHeader("Factory Menu")
                        FactoryMenuList(MENU_ITEMS)
                    }
                }
            }
        }
    }

    companion object {
        private val MENU_ITEMS =
            listOf(
                "Device Info",
                "Diagnostic",
                "Panel Diagnostic",
                "Audio Diagnostic",
                "Dap Parameter",
                "USB Operations",
                "CI+",
                "Non-Linear Adjust",
                "SSC Adjust",
                "Peq Adjust",
                "Channel Preset",
                "OLED Maintenance",
                "Other Options",
                "Test Pattern",
                "Device Lifetime",
                "Factory Reset",
                "Factory Exit"
            )
    }
}

@Composable
private fun FactoryMenuList(greetings: List<String> = List(10) { "$it" }) {
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
                    "Factory Exit" -> {
                        exitProcess(0)
                    }

                    "Factory Reset" -> {
                        showDialog.value = true
                    }

                    else -> {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )
        }
    }
}