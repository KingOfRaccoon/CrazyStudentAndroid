package ru.kingofraccoons.crazystudent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ru.kingofraccoons.crazystudent.presentation.navigation.EnrolleeMainNavigation
import ru.kingofraccoons.crazystudent.presentation.navigation.MainNavigation
import ru.kingofraccoons.crazystudent.presentation.routes.GroupRoutes
import ru.kingofraccoons.crazystudent.presentation.routes.ScreenRoutes
import ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.AiEventCreationScreen
import ru.kingofraccoons.crazystudent.presentation.screens.start.AuthorizationScreen
import ru.kingofraccoons.crazystudent.presentation.screens.start.RegistrationScreen
import ru.kingofraccoons.crazystudent.presentation.screens.start.SplashScreen
import ru.kingofraccoons.crazystudent.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

typealias NavigationFun = () -> Unit

@Composable
fun App() = AppTheme {
    val navigator = rememberNavController()

    val onAuthEndRoute = if (handleIncomingText().isNullOrBlank()) {
        "main"
    } else {
        "aiEvent"
    }

    navigator.currentBackStackEntry
    NavHost(
        navigator, GroupRoutes.Start.name,
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        navigation(ScreenRoutes.Start.Splash.name, GroupRoutes.Start.name) {
            composable(ScreenRoutes.Start.Splash.name) {
                SplashScreen(
                    { navigator.navigate(ScreenRoutes.Start.Authorization.name) },
                    { navigator.navigate(ScreenRoutes.Student.MainStudent.name) },
                    { navigator.navigate(ScreenRoutes.Enrollee.MainEnrollee.name) }
                )
            }
            composable(ScreenRoutes.Start.Authorization.name) {
                AuthorizationScreen(
                    { navigator.navigate(ScreenRoutes.Start.Registration.name) },
                    { navigator.navigate(onAuthEndRoute) },
                    { navigator.navigate(ScreenRoutes.Enrollee.MainEnrollee.name) }
                )
            }
            composable(ScreenRoutes.Start.Registration.name) {
                RegistrationScreen(
                    { navigator.navigate(ScreenRoutes.Start.Authorization.name) },
                    { navigator.navigate(ScreenRoutes.Student.MainStudent.name) },
                    { navigator.navigate(ScreenRoutes.Enrollee.MainEnrollee.name) }
                )
            }
        }
        composable("main") {
            MainNavigation(navigator)
        }
        composable(
            route = "aiEvent",
        ) {
            AiEventCreationScreen()
        }
        composable(ScreenRoutes.Enrollee.MainEnrollee.name) {
            EnrolleeMainNavigation(navigator)
        }
    }
}

@Composable
fun handleIncomingText(): String? {
    val context = LocalContext.current
    val activity = context as? Activity
    val intent = activity?.intent

    if (intent != null && intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
        return intent.getCharSequenceExtra(Intent.EXTRA_TEXT).toString()
    }
    return null
}