package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.kingofraccoons.crazystudent.handleIncomingText
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme

@Composable
fun AiEventCreationScreen(modifier: Modifier = Modifier) {

    val sharedText = handleIncomingText()
    if (sharedText.isNullOrEmpty()) {
        Text("no incoming text")
        return
    }
    Text(sharedText)
}


@Preview
@Composable
private fun AiEventCreationScreenPreview() {
    CrazyStudentTheme {
        Box(modifier = Modifier.background(Color.Black)) {
            AiEventCreationScreen()
        }
    }
}