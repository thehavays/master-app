package com.vestel.tv.masterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import com.vestel.tv.masterapp.ui.deviceinfo.DeviceInfoActivity
import com.vestel.tv.masterapp.ui.diagnostic.DiagnosticActivity
import com.vestel.tv.masterapp.ui.mainmenu.MainMenuActivity
import com.vestel.tv.masterapp.ui.theme.MasterAppTheme

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

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "MAIN_ACTIVITY",
                    ) {
                        composable("MAIN_ACTIVITY") {
                            MainMenuActivity(navController, MENU_ITEMS)
                        }
                        composable("DEVICE_INFO") {
                            DeviceInfoActivity(navController)
                        }
                        composable("DIAGNOSTIC") {
                            DiagnosticActivity(navController)
                        }
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