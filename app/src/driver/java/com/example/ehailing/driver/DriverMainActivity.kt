package com.example.ehailing.driver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ehailing.ui.screens.*
import com.example.ehailing.ui.theme.EHailingTheme

class DriverMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EHailingTheme {
                DriverApp()
            }
        }
    }
}

object DriverRoutes {
    const val LOGIN = "driver_login"
    const val HOME = "driver_home"
    const val TRACKING = "driver_tracking"
    const val COMPLETE = "driver_complete"
}

@Composable
private fun DriverApp() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = DriverRoutes.LOGIN) {
        composable(DriverRoutes.LOGIN) {
            LoginScreen(
                onContinue = {
                    nav.navigate(DriverRoutes.HOME) {
                        popUpTo(DriverRoutes.LOGIN) { inclusive = true }
                    }
                },
                onSignUp = { },
            )
        }
        composable(DriverRoutes.HOME) {
            DriverHomeScreen(onAcceptRide = { nav.navigate(DriverRoutes.TRACKING) })
        }
        composable(DriverRoutes.TRACKING) {
            TrackingScreen(
                onComplete = {
                    nav.navigate(DriverRoutes.COMPLETE) {
                        popUpTo(DriverRoutes.TRACKING) { inclusive = true }
                    }
                },
            )
        }
        composable(DriverRoutes.COMPLETE) {
            RideCompleteScreen(
                onDone = {
                    nav.navigate(DriverRoutes.HOME) {
                        popUpTo(DriverRoutes.COMPLETE) { inclusive = true }
                    }
                },
            )
        }
    }
}