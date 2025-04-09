package ru.kingofraccoons.crazystudent.presentation.screens.start.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.domain.entity.response.userdata.User
import ru.kingofraccoons.crazystudent.domain.util.Resource

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SplashScreen(
    navigateToAuth: NavigationFun,
    navigateToRegistration: NavigationFun,
    navigateToMainStudent: NavigationFun,
    viewModel: SplashViewModel = koinInject(),
) {
    var fractionWidth by remember { mutableFloatStateOf(0.1f) }
    val user by viewModel.userFlow.collectAsState(Resource.Loading<User>() to null)

    LaunchedEffect(Unit) {
        viewModel.loginOnToken()
    }

    LaunchedEffect(Unit) {
        animate(0.1f, 1f, animationSpec = tween(1500)) { value, _ ->
            fractionWidth = value
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
//        BoxWithConstraints(Modifier.fillMaxWidth(0.9f), contentAlignment = Alignment.Center) {
            Image(
                painterResource(R.drawable.logo),
                "",
                Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
                    .scale(fractionWidth)
            )
//        }
        Spacer(Modifier.weight(1f))
    }

    if (fractionWidth == 1f) {
        LaunchedEffect(Unit) {
            delay(300)
            if (user.second == "") {
                navigateToRegistration()
                return@LaunchedEffect
            }

            if (user.first.isSuccess())
                navigateToMainStudent()
            else
                navigateToAuth()
        }
    }
}