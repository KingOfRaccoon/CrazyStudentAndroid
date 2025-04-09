package ru.kingofraccoons.crazystudent.presentation.screens.main.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme


@Composable
fun InternshipDetailsScreen() {
    val internship = data.last()


        Column(Modifier
            .padding(16.dp)) {

            Card(
                modifier = Modifier.size(50.dp),
                colors = CardDefaults.cardColors(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(internship.icon),
                    contentDescription = "icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

        }

        val uriHandler = LocalUriHandler.current
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF70DDFF)),
            onClick = { uriHandler.openUri(internship.redirect) }
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BodyText("Откликнуться", modifier = Modifier)
                Icon(Icons.Default.ArrowForward, "")
            }
        }


}


@Composable
@Preview
fun InternshipDetailsScreenPreview() {
    CrazyStudentTheme {
        InternshipDetailsScreen()
    }
}