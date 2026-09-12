package com.example.ehailing.rider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ehailing.data.local.ProfileStore
import com.example.ehailing.ui.screens.auth.*
import com.example.ehailing.ui.screens.home.*
import com.example.ehailing.ui.screens.ride.RideCompleteScreen
import com.example.ehailing.ui.screens.ride.TrackingScreen
import com.example.ehailing.ui.theme.EHailingTheme

class RiderMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileStore = ProfileStore(this)
        setContent {
            EHailingTheme {
                RiderApp(profileStore = profileStore)
            }
        }
    }
}

object RiderRoutes {
    const val SPLASH = "splash"; const val WELCOME = "welcome"
    const val PHONE = "phone";   const val OTP = "otp"
    const val PROFILE = "profile"; const val HOME = "home"
    const val SEARCH = "search"; const val RIDE_SELECT = "ride_select"
    const val MATCHING = "matching"; const val TRACKING = "tracking"
    const val COMPLETE = "complete"
}

@Composable
private fun RiderApp(profileStore: ProfileStore) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = RiderRoutes.SPLASH) {

        composable(RiderRoutes.SPLASH) {
            SplashScreen(onFinish = { loggedIn ->
                val next = if (loggedIn) RiderRoutes.HOME else RiderRoutes.WELCOME
                nav.navigate(next) { popUpTo(RiderRoutes.SPLASH) { inclusive = true } }
            })
        }
        composable(RiderRoutes.WELCOME) {
            WelcomeScreen(
                onGetStarted = { nav.navigate(RiderRoutes.PHONE) },
                onLogIn = { nav.navigate(RiderRoutes.PHONE) },
                onLanguageClick = { },
            )
        }
        composable(RiderRoutes.PHONE) {
            PhoneEntryScreen(
                onBack = { nav.popBackStack() },
                onContinue = { phone, _ -> nav.navigate("${RiderRoutes.OTP}/$phone") },
            )
        }
        composable("${RiderRoutes.OTP}/{phone}") { bs ->
            val phone = bs.arguments?.getString("phone").orEmpty()
            OtpVerifyScreen(
                phone = phone,
                onBack = { nav.popBackStack() },
                onVerified = { nav.navigate("${RiderRoutes.PROFILE}/$phone") },
            )
        }
        composable("${RiderRoutes.PROFILE}/{phone}") { bs ->
            val phone = bs.arguments?.getString("phone").orEmpty()
            ProfileSetupScreen(
                phone = phone,
                onBack = { nav.popBackStack() },
                onDone = { name, email, gender ->
                    profileStore.saveProfile(name, email, phone, gender)
                    nav.navigate(RiderRoutes.HOME) {
                        popUpTo(RiderRoutes.WELCOME) { inclusive = true }
                    }
                },
            )
        }
        composable(RiderRoutes.HOME) {
            HomeMapScreen(
                onSearchClick = { nav.navigate(RiderRoutes.SEARCH) },
                onRecentClick = { name -> nav.navigate("${RiderRoutes.RIDE_SELECT}/$name") },
                onProfileClick = { },
            )
        }
        composable(RiderRoutes.SEARCH) {
            LocationSearchScreen(
                onBack = { nav.popBackStack() },
                onSelectPlace = { place ->
                    nav.navigate("${RiderRoutes.RIDE_SELECT}/${place.name}") {
                        popUpTo(RiderRoutes.SEARCH) { inclusive = true }
                    }
                },
            )
        }
        composable("${RiderRoutes.RIDE_SELECT}/{dest}") { bs ->
            val dest = bs.arguments?.getString("dest").orEmpty()
            RideSelectionScreen(
                destinationName = dest,
                onBack = { nav.popBackStack() },
                onConfirm = { _ -> nav.navigate(RiderRoutes.MATCHING) },
            )
        }
        composable(RiderRoutes.MATCHING) {
            MatchingScreen(
                onCancel = { nav.popBackStack() },
                onMatched = {
                    nav.navigate(RiderRoutes.TRACKING) {
                        popUpTo(RiderRoutes.MATCHING) { inclusive = true }
                    }
                },
            )
        }
        composable(RiderRoutes.TRACKING) {
            TrackingScreen(
                onComplete = {
                    nav.navigate(RiderRoutes.COMPLETE) {
                        popUpTo(RiderRoutes.TRACKING) { inclusive = true }
                    }
                },
            )
        }
        composable(RiderRoutes.COMPLETE) {
            RideCompleteScreen(
                onDone = {
                    nav.navigate(RiderRoutes.HOME) {
                        popUpTo(RiderRoutes.COMPLETE) { inclusive = true }
                    }
                },
            )
        }
    }
}