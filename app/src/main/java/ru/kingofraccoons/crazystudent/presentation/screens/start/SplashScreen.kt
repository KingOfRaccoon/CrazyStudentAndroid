package ru.kingofraccoons.crazystudent.presentation.screens.start

import android.annotation.SuppressLint
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SplashScreen(
    navigateToAuth: NavigationFun,
    navigateToMainStudent: NavigationFun,
    navigateToMainEnrollee: NavigationFun,
) {
    var fractionWidth by remember { mutableFloatStateOf(0.1f) }
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
        BoxWithConstraints(Modifier.fillMaxWidth(0.9f), contentAlignment = Alignment.Center) {
            Image(
                painterResource(R.drawable.logo),
                "",
                Modifier
                    .fillMaxSize()
                    .scale(fractionWidth)
            )
        }
        Spacer(Modifier.weight(1f))
    }

    if (fractionWidth == 1f) {
        LaunchedEffect(Unit) {
            delay(300)
            navigateToAuth()
        }
    }
}